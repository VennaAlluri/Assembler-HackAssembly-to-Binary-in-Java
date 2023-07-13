class Bin {
    public static String con (String a) {
        int x=Integer.parseInt(a);
        int m=1;
        int[] bit =new int [15];
        String str="";
        for (int i=14;i>=0;i--) {
            int temp = x>>i;
            bit[i] = temp&m;
            str = str+String.valueOf(bit[i]);
        }
        return str;
    }
}