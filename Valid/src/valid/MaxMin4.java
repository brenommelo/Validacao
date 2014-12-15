package valid;
public class MaxMin4 {
  public static int [] maxMin4 (int v[], int linf, int lsup) {
    int maxMin[] = new int[2];
    if (lsup - linf <= 1) { 
      if (v[linf] < v[lsup]) { maxMin[0] = v[lsup]; maxMin[1] = v[linf]; }
      else { maxMin[0] = v[linf]; maxMin[1] = v[lsup]; }
    }
    else {
      int meio = (linf + lsup)/2;
      maxMin = maxMin4 (v, linf, meio);
      int max1 = maxMin[0], min1 = maxMin[1];
      maxMin = maxMin4 (v, meio + 1, lsup);
      int max2 = maxMin[0], min2 = maxMin[1];
      if (max1 > max2) maxMin[0] = max1; else maxMin[0] = max2;
      if (min1 < min2) maxMin[1] = min1; else maxMin[1] = min2;
    }
    return maxMin;
  }
  public static int posMax (int v[]){
       int maxMin[] = new int[2];
        maxMin = maxMin4(v, 0, v.length-1);
        for (int i = 0; i < v.length; i++) {
          if(v[i]==maxMin[0]){
              return i;
          }
      }
      
        return 0;
  }

//  public static void main (String[] args) {
//    int v[] = new int[11];
//    v[0] = 5;  v[1] = 12;
//    v[2] = 4;  v[3] = 1;
//    v[4] = 9; v[5] = 22;
//    v[6] = 3; v[7] = 11;
//    v[8] = 17; v[9] = 33;
//    v[10] = 15;
//    int maxMin[] = MaxMin4.maxMin4 (v, 0, 10);
//    System.out.println ("Max:" + maxMin[0]);
//    System.out.println ("Min:" + maxMin[1]);
//  }
}
