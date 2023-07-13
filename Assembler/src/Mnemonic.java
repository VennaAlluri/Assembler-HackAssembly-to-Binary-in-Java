import java.util.HashMap;
import java.util.Objects;

public class
Mnemonic {
    HashMap<String,String>  Data;
    HashMap<String,String>  Dest;
    Mnemonic (){
        Data = new HashMap<>();
        Data.put("@","0");
        Data.put("SCREEN","100000000000000");
        Data.put("KBD","110000000000000");
        Data.put("ARG",Bin.con("2"));
        Data.put("LCL",Bin.con("1"));
        Data.put("THIS",Bin.con("3"));
        Data.put("THAT",Bin.con("4"));
        Data.put("SP",Bin.con("0"));
        Data.put("0","101010");
        Data.put("1","111111");
        Data.put("-1","111010");
        Data.put("D","001100");
        Data.put("A","110000");
        Data.put("!D","001101");
        Data.put("!A","110001");
        Data.put("-D","001111");
        Data.put("-A","110011");
        Data.put("D+1","011111");
        Data.put("A+1","110111");
        Data.put("D-1","001110");
        Data.put("A-1","110010");
        Data.put("D+A","000010");
        Data.put("A+D","000010"); ///////////////
        Data.put("D-A","010011");
        Data.put("A-D","000111");
        Data.put("D&A","000000");
        Data.put("D|A","010101");
        Data.put("NULL","000");
        Data.put("JGT","001");
        Data.put("JEQ","010");
        Data.put("JGE","011");
        Data.put("JLT","100");
        Data.put("JNE","101");
        Data.put("JLE","110");
        Data.put("JMP","111");

        Dest=new HashMap<>();
        Dest.put("M","001");
        Dest.put("D","010");
        Dest.put("MD","011");
        Dest.put("A","100");
        Dest.put("AM","101");
        Dest.put("AD","110");
        Dest.put("AMD","111");


    }
    public String get(String str1,String str2){
        if (Objects.equals(str1, "Dest")){
            return Dest.get(str2);
        }
        else if (Objects.equals(str1, "Data")){
            return Data.get(str2);
        }
        else {
            return null;
        }
    }
    public static void main(String[] args){

    }
}