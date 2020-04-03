/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

import Negocio.Log;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author jorge.correa
 */
public class ProgramacionCambiarEstadoPago {
   
    public void crearProgramacion() {
        
        Integer intError=0;
        
        try{

            Log.registroTraza("Inició la programación de la tarea CambiarEstadoPago");
              
            intError = 1;
            
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start(); 
            
            intError = 2;
            
            JobDetail jobDetail = new JobDetail("CambiarEstadoPagoJob", scheduler.DEFAULT_GROUP, CambiarEstadoPago.class);
            
            intError = 3;
              
            Trigger trigger = TriggerUtils.makeDailyTrigger(05, 30);
            trigger.setName("tgCambiarEstadoPago");
            trigger.setGroup("grupoSIU");
            trigger.setPriority(3);
            
             intError = 4;

            // Registro dentro del Scheduler.            
            scheduler.scheduleJob(jobDetail, trigger);
            
            intError = 5;
             
            Log.registroTraza("Finalizó la programación de la tarea CambiarEstadoPago");
              
        }catch(SchedulerException se) {
            Log.registroTraza("Se generó un error al ejecutar la tarea CambiarEstadoPago. Variable intError = " +  intError + ", " + se.getMessage());
        }catch(Exception e) {
            Log.registroTraza("Se generó un error al ejecutar la tarea CambiarEstadoPago. Variable error = " +  intError + ", " + e.getMessage());
        }              
    }
}
