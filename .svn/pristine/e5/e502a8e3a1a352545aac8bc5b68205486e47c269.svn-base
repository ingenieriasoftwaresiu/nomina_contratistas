/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import Negocio.Log;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Web application lifecycle listener.
 *
 * @author jorge.correa
 */
public class EjecutarTareas implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            new ProgramacionCambiarEstadoContrato().crearProgramacion();
            new ProgramacionCambiarEstadoPago().crearProgramacion();
            new ProgramacionNotificarContratosAVencerseAdministrador().crearProgramacion();
            new ProgramacionNotificarContratosAVencerseInterventor().crearProgramacion();
        } catch (Exception ex) {
            Log.registroTraza(ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try{
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.shutdown(false);
        } catch (Exception ex) {
            Log.registroTraza(ex.getMessage());
        }
    }
}
