/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jobs;

/**
 *
 * @author jorge.correa
 */
public class LanzadorNotificarContratosAVencerseInterventor {
    public static void main(String[] args) {
        try {
            new ProgramacionNotificarContratosAVencerseInterventor().crearProgramacion();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
