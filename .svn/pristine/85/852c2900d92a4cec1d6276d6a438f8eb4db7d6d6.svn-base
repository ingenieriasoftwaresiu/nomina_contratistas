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
public class ProgramacionNotificarContratosAVencerseInterventor {

    public void crearProgramacion() {
        
        Integer intError=0;
        
        try {
            
            Log.registroTraza("Inició la programación de la tarea NotificarContratosAVencerseInterventor");
            
            intError = 1;
            
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();             
            scheduler.start(); 
            
            intError = 2;
            
            // Creación de una instancia de JobDetail.
            JobDetail jobDetail = new JobDetail("NotificarContratosAVencerseInterventor", Scheduler.DEFAULT_GROUP, NotificarContratosAVencerseInterventor.class);
            
            intError = 3;
            
            // Se crea el trigger para ejecución todos los días a las 6:30 a.m.          
            Trigger trigger = TriggerUtils.makeDailyTrigger(06, 30);
            trigger.setName("tgNotificarContratosAVencerseInterventor");
            trigger.setGroup("grupoSIU");
            trigger.setPriority(1);
                       
             intError = 4;

            // Registro dentro del Scheduler.
            scheduler.scheduleJob(jobDetail, trigger);
                 
            intError = 5;

            Log.registroTraza("Finalizó la programación de la tarea NotificarContratosAVencerseInterventor");  

        }catch(SchedulerException se) {
            Log.registroTraza("Se generó un error al ejecutar la tarea NotificarContratosAVencerseInterventor. Variable intError = " +  intError + ", " + se.getMessage());
        }catch(Exception e) {
            Log.registroTraza("Se generó un error al ejecutar la tarea NotificarContratosAVencerseInterventor. Variable intError = " +  intError + ", " + e.getMessage());
        }        
    }
}
