package helpers;

import api.services.EventoServices;
import api.services.QuedadaServices;
import api.services.UsuarioServices;
import entities.Evento;
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
    private Niveles niveles;

    @Autowired
    private QuedadaServices quedadaServices;

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private EventoServices eventoServices;

    @Override
    public void run() {
        niveles = Niveles.getInstance();
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
        int nivel;
        for (Map.Entry<String, Integer> value : puntosUsuarios.entrySet()) {
            user = usuarioServices.findByEmail(value.getKey());
            user.setPuntos(user.getPuntos() + value.getValue());
            nivel = niveles.getNivelPorPuntos(user.getPuntos());
            if (nivel != user.getNivel()){
                generarEvento(user);
                user.setNivel(nivel);
            }
            usuarioServices.updateUsuario(user);
        }
    }

    private void generarEvento(Usuario user){
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Calendar nextDay = Calendar.getInstance();
        nextDay.setTime(new Date());
        nextDay.add(Calendar.DATE,1);
        String desc = user.getEmail() + "sube a nivel" + user.getNivel();
        Evento evento = new Evento();
        evento.setDescripcion(desc);
        evento.setTitulo(desc);
        evento.setUsuario(user.getEmail());
        evento.setFecha(today.getTime());
        evento.setNotificaciones(true);
        evento.setFechaFin(nextDay.getTime());
        eventoServices.altaEvento(evento);
    }

    public void lanzar(){
        int minuto = 60000;
        int periodo = minuto * 30;
        timer.schedule(this,0,periodo);
    }

}
