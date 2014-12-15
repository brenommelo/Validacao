/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testesssssss;

/**
 *
 * @author breno.melo
 */
public class Testessssssss {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                teteRemoverMaskCpf();
//                        final ImagemTeste dialog = new ImagemTeste();
//                        dialog.setVisible(true);
            }
        });

    }
      
    public static void teteRemoverMaskCpf(){
        String str = "080.673.626-70";
        
        str = str.replace(".", "");
        str = str.replace("-", "");
        
        System.out.println(str);
    
    }
}
