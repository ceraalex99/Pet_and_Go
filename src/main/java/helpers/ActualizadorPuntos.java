package helpers;

import api.services.EventoServices;
import api.services.QuedadaServices;
import api.services.UsuarioServices;
import entities.Evento;
import entities.Mascota;
import entities.Quedada;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ActualizadorPuntos extends TimerTask {

    private Map<String,Integer> puntosUsuarios;
    private Niveles niveles;

    @Autowired
    private QuedadaServices quedadaServices;

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private EventoServices eventoServices;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Override
    public void run() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                niveles = Niveles.getInstance();
                actualizarPerrParadasFinalizadas();
                actualizarNivel();
            }
        });
    }

    void actualizarPerrParadasFinalizadas(){
        List<Quedada> quedadasFinalziadas = quedadaServices.getPendientesFinalizar();
        puntosUsuarios = new HashMap<>();
        List<String> participantesEmail;
        Set<Mascota> participantes;
        int puntos;
        for (Quedada q : quedadasFinalziadas) {
            if (!q.getParticipantes().isEmpty()){
                participantes = q.getParticipantes();
                participantesEmail = participantes.stream().map(m -> m.getId().getAmo()).distinct().collect(Collectors.toList());
                puntos = participantes.size();
                for (String emailUser: participantesEmail){
                    actualizarPuntos(emailUser,puntos);
                }
                if (puntos > 0)
                    actualizarPuntos(q.getAdmin(),(int) (puntos * 1.10));
                q.setFinalizada(true);
            }
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
                user.setNivel(nivel);
                generarEvento(user);
            }
        }
    }

    private void generarEvento(Usuario user){
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Calendar nextDay = Calendar.getInstance();
        nextDay.setTime(new Date());
        nextDay.add(Calendar.DATE,1);
        String desc = user.getEmail() + " sube a nivel " + user.getNivel();

        Evento evento = new Evento();
        evento.setDescripcion(desc);
        evento.setTitulo(desc);
        evento.setUsuario(user.getEmail());
        evento.setFecha(today.getTime());
        evento.setNotificaciones(true);
        evento.setFechaFin(nextDay.getTime());
        eventoServices.altaEvento(evento);
    }

    public void init(){
        Timer timer = new Timer();
        int minuto = 60000;
        int periodo = minuto * 30;
        timer.schedule(this,10,periodo);
    }

}
