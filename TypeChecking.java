/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typechecking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.exit;

/**
 *
 * @author dell7520
 */
public class TypeChecking {
    static String g="";
    static String table[][]=new String[10][2];
    static int x=-1;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter the expression");
        String s=br.readLine();
        String ss[]=s.split(" ");
        for(int i=0;i<ss.length;++i)
            chk(ss[i]);
        System.out.println("");
        System.out.println("enter the expression for type checking");
        s=br.readLine();
        String ss00[]=s.split("=");
        String z= ss00[0];
        int y;
        for(y=0;y<=x;++y)
        {
            if(z.equals(table[y][0]))
            {
                z=table[y][1];
                break;
            }
        }
        String sx="";
        if(y>x)
        {
            System.out.println("not in symbol table");
            exit(0);
        }
        String ss0[]=ss00[1].split(" + ");
        
        for(int i=0;i<ss0.length;++i)
        {
            for(y=0;y<x;++y)
            {
                if(ss0[y].equals(table[y][0]))
                {
                    sx=table[y][1];
                    break;
                }
            }
            if(y>x)
            {
                System.out.println("not in symbol table");
                exit(0);
            }
            else
            {
                if(!(sx.equals(z)))
                {
                    System.out.println("Type not matched");
                    exit(0);
                }
            }
        }
        System.out.println("Type matched");
    }
    public static void chk(String s)
    {
        try{
            Long.parseLong(s);
            System.out.println(s+"\tInteger");
        }
        catch(Exception e)
        {
            try{
                Double.parseDouble(s);
                if(!s.contains("e"))
                    System.out.println(s+"\tDecimal");
                else
                    System.out.println(s+"\tExponent");
            }
            catch(Exception e0)
            {
                String ss=s.replaceFirst("e","");
                try{
                    Double.parseDouble(ss);
                    System.out.println(s+"\tExponent");
                }
                catch(Exception e1)
                {
                    ss=(s.replace(" ","")).replaceFirst("i","");
                    String ss0=ss.replaceFirst("[+]",""), ss1=ss.replaceFirst("[-]","");
                    try
                    {
                        Double.parseDouble(ss0);
                        if(ss0.contains("e")||ss1.contains("e"))
                            System.out.println(s+"\tOther");
                        else
                            System.out.println(s+"\tComplex");
                    }
                    catch(Exception e2)
                    {
                        try{
                            Double.parseDouble(ss1);
                            System.out.println(s+"\tComplex");
                        }catch(Exception e3){
                            if(Character.isLetter(s.charAt(0)))
                            {
                                if(s.matches("[a-zA-Z0-9]+"))
                                {
                                    if(s.equals("int")||s.equals("float")||s.equals("double")||s.equals("long"))
                                    {
                                        g=s;
                                        System.out.println(s+"\tKeyword");
                                    }
                                    else if(g!="")
                                    {
                                        System.out.println(s+"\t"+g);
                                        table[++x][0]=s;
                                        table[x][1]=g;
                                    }
                                    
                                    else
                                        System.out.println(s+"\tIdentifier");
                                }
                                else
                                    System.out.println(s+"\tOthers");
                            }
                            else
                                System.out.println(s+"\tOther");
                        }
                    }
                }
            }
        }
    }
}

