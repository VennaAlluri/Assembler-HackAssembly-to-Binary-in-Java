import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B11_Assembler  {
    private Mnemonic mnm = new Mnemonic();
    ArrayList<String> Binary =new ArrayList<>();
    HashMap<String,String> tab=new HashMap<>();
  //  Pattern pat;
    private int count=16;
    int line_count =0;
    public void A_inst(String A){
        StringBuffer b = new StringBuffer(A);
        String s="@";
        String label="";
        if (b.charAt(0)=='@'){
            b.deleteCharAt(0);
           if (mnm.Data.containsKey(b.toString()) && (b.toString().matches("\\D+")) && (!b.toString().equals("A")) && (!b.toString().equals("D")) && !b.toString().equals("D") ){
               label=mnm.get("Data",s)+mnm.get("Data",b.toString());
           }
           else if(b.charAt(0)=='R'){
                b.deleteCharAt(0);
                if(b.toString().matches("\\d+")) {//
                    label = mnm.get("Data", s) + Bin.con(b.toString());
                }
            }

            else if(Pattern.compile("[A-Za-z_]+").matcher(b.toString()).find()){
                if(tab.containsKey(b.toString())){

                    label = mnm.get("Data", s) + tab.get(b.toString());
                }
                else {
                    label = mnm.get("Data", s) + Bin.con(String.valueOf(count));

                    tab.put(b.toString(),Bin.con(String.valueOf(count)));
                    count += 1;
                }
            }
            else if(b.toString().matches("[0-910-24576]+")){
                label=mnm.get("Data",s)+Bin.con(b.toString());
           }
        }
        Binary.add(label);
    }

    public void C_inst(String C){
        String s="111";
        String dest;
        String jump="NULL"; String a="0";
        String label="";
        if(C.contains("=")){
            String[]c=C.split("=");
            if(mnm.Data.containsKey(c[1])){
                label=s+a+mnm.get("Data",c[1])+mnm.get("Dest",c[0])+mnm.get("Data",jump);
            }
            else{
                c=C.split("=");
                c[1]=c[1].replaceAll("M","A");
                a="1";
                label=s+a+mnm.get("Data",c[1])+mnm.get("Dest",c[0])+mnm.get("Data",jump);

            }
        }
        else{
            String[] c=C.split(";");
            dest="NULL";
            jump=mnm.get("Data",c[1]);
            label=s+a+mnm.get("Data",c[0])+mnm.get("Data",dest)+jump;
        }
        //System.out.println(label);
        Binary.add(label);
    }

    public void hack(String path) throws IOException {
        FileReader read1 =new FileReader(path);
        BufferedReader buffer_read1 =new BufferedReader(read1);
        String line="";
        String[] c=new String[1];
        // pat= Pattern.compile("^[\\(]"); //
        while((line= buffer_read1.readLine())!=null){
            if(line.length()!=0 && new StringBuffer(line).charAt(0)!='/'){
                if(Pattern.compile("^[\\(]").matcher(line).find()) {
                    String x = line.replaceAll("[\\(]|[\\)]", "");
                    x = x.replaceAll("\\s", "");
                    c = x.split("//");
                    tab.put(c[0], Bin.con(String.valueOf(line_count)));
                    line_count -= 1;
                }
                    else if(line.matches("^[\\(][A-Za-z_]+[\\)]$")){
                        String x = line.replaceAll("[\\(]|[\\)]", "");
                        x=x.replaceAll("\\s","");
                        tab.put(x, Bin.con(String.valueOf(line_count)));
                       line_count-=1;
                    }

                line_count+=1;
            }
        }
        buffer_read1.close();
      //  Pattern pat2=Pattern.compile("^[@][A-Za-z0-910-24576_]+"); //
        FileReader read2 =new FileReader(path);
        BufferedReader buffer_read2 =new BufferedReader(read2);
        while ((line= buffer_read2.readLine())!=null) {
            if(line.length()!=0 && new StringBuffer(line).charAt(0)!='/'){
               line=line.replaceAll("\\s","");
                if(Pattern.compile("^[@][A-Za-z0-910-24576_]+").matcher(line).find()){
                    if(line.contains("//")){
                        c=line.split("//");
                        A_inst(c[0]);

                    }
                    else{
                        A_inst(line);
                    }
                }


                else if(Pattern.compile("^[\\(]").matcher(line).find()) {
                    continue;
                }

               else {
                    if(line.contains("//")){
                        c=line.split("//");
                        C_inst(c[0]);
                    }
                    else{
                        C_inst(line);
                    }
                }
            }
        }
        buffer_read2.close();
    }

    public void write_file(String path) throws Exception {
        File f =new File(path);
        if(f.exists()){
            throw new Exception("!This file is already exist");
        }
        else{
            FileWriter write = new FileWriter(f);
            BufferedWriter buffer_write=new BufferedWriter(write);
            for (String s:Binary){
                buffer_write.write(s+"\n");
            }
            buffer_write.close();
        }
}

public static void main(String[] args) throws Exception {
        B11_Assembler obj = new B11_Assembler();
        obj.hack("C:\\Users\\venna\\OneDrive\\Documents\\nand2tetris\\nand2tetris\\tools\\Main.asm");
        obj.write_file("C:\\Users\\venna\\OneDrive\\Documents\\nand2tetris\\nand2tetris\\tools\\Main.hack");

    }
}

