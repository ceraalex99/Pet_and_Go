package helpers;

import api.services.QuedadaServices;
import api.services.UsuarioServices;
import entities.Mascota;
import entities.Quedada;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.stream.Collectors;

public class ActualizadorPuntos extends TimerTask {

    private List<Quedada> quedadasFinalziadas;
    private Timer timer;
    private Map<String,Integer> puntosUsuarios;


    @Autowired
    @Qualifier("quedadaservices")
    private QuedadaServices quedadaServices;

    @Autowired
    @Qualifier("usuarioservices")
    private UsuarioServices usuarioServices;

    @Override
    public void run() {
        actualizarPerrParadasFinalizadas();
        actualizarNivel();
    }

    private void actualizarPerrParadasFinalizadas(){
        quedadasFinalziadas = quedadaServices.getPendientesFinalizar();
        puntosUsuarios = new HashMap<String,Integer>();
        List<String> participantes;
        int puntos;
        for (Quedada q : quedadasFinalziadas) {
            participantes = q.getParticipantes().stream().map(m -> m.getId().getAmo()).distinct().collect(Collectors.toList());
            puntos = participantes.size();
            for (String emailUser: participantes){
               actualizarPuntos(emailUser,puntos);
            }
            if (puntos > 0)
                actualizarPuntos(q.getAdmin(),(int) (puntos * 1.10));
            q.setFinalizada(true);
            quedadaServices.updateQuedada(q);
        }
    }

    private void actualizarPuntos(String emailUser, int puntos) {
        if(puntosUsuarios.containsKey(emailUser))
            puntosUsuarios.put(emailUser,puntosUsuarios.get(emailUser) + puntos );
        else
            puntosUsuarios.put(emailUser,puntos );
    }

    private void actualizarNivel(){
        Usuario user;
        for (Map.Entry<String, Integer> value : puntosUsuarios.entrySet()) {
            user = usuarioServices.findByEmail(value.getKey());
            user.setPuntos(user.getPuntos() + value.getValue());
            usuarioServices.updateUsuario(user);
        }
    }

    private void generarEvento(){
        //pendiente
    }

    public void lanzar(){
        //Pendiente definir
        timer.schedule(this,10,1000);
    }

}
