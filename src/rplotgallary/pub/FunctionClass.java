/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rplotgallary.pub;

import java.io.File;

/**
 *
 * @author zhaoqi
 */
public class FunctionClass {
    //get parameter after one specified name
    public static String getArgsParameter(String[] args, String parameter){
        int marker=0;
        String str="";
        for (int i = 0; i < args.length-1; i++) {
            if(args[i].equalsIgnoreCase(parameter)){
                marker=1;
                str= args[i+1];
                break;
            }
        }
        if(marker==0||str.startsWith("-")){
            return null;
        }else{
            return str;
        }
    } 
    public static void main(String[] args) {
        String[] a= {"A","B"};
        System.out.println(FunctionClass.getArgsParameter(a, "A"));
    }
    //remove Routfile
    public static void removeRoutFile(String rscriptpath ){
        File rscriptFile=new File(rscriptpath);
             String outfile=rscriptFile.getAbsolutePath().split("\\.")[0]+".Rout";
             System.out.println(outfile);
             new File(outfile).delete();
    }
}

