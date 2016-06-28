
import java.io.IOException;


public class Multiply {

	static int  cost;
	
	public static void main(String[] args) throws IOException {
		 
		 String strnumberA = "123456789";
		 String strnumberB = "123456";
		 String strResult;
		 int[] arrayIntnumA = StrToArrayInt(strnumberA,strnumberA.length());
		 int[] arrayIntnumB = StrToArrayInt(strnumberB,strnumberB.length());
		 int[] arrayResult;
		 
		 long time_start, time_end;
		 
		 /*
		 cost=0;
		 System.out.println("\nMultipliacion con SUMA DE DOS VALORES****************************\n");
		 time_start = System.currentTimeMillis();		
		 arrayResult = SumTwoValues(arrayIntnumA,arrayIntnumB);		 
		 time_end = System.currentTimeMillis();
		 strResult=ArrayInToStr(arrayResult);
		 System.out.println("Tiempo de ejecucion: "+ (( time_end - time_start )*0.001) +" Second");
		 System.out.println("Costo: "+cost);
		 System.out.println("El resultado de la Suma es: "+strResult);
		 */
		 
		 /*
		 cost=0;
		 System.out.println("\nMultipliacion con SUMA CONTINUA****************************\n");
		 time_start = System.currentTimeMillis();		
		 arrayResult = OverLodadMulti(arrayIntnumA,arrayIntnumB);		 
		 time_end = System.currentTimeMillis();
		 strResult=ArrayInToStr(arrayResult);
		 System.out.println("Tiempo de ejecucion: "+ (( time_end - time_start )*0.001) +" Second");
		 System.out.println("Costo: "+cost);
		 System.out.println("El resultado de la multiplicacion con es: "+strResult);
		 */
		 
		 /*
		 int[]x = StrToArrayInt(numberA);
		 int[]y = StrToArrayInt(numberB);
		 
		 x=PutEqualAndEvenIndex(x, y.length);
		 y=PutEqualAndEvenIndex(y, x.length);
		 
		 cost=0;
		 System.out.println("\nMultipliacion con KARATSUBA****************************\n");
		 time_start = System.currentTimeMillis();	
		 result = Integer.toString(KaratsubaMulti(x, y));
		 time_end = System.currentTimeMillis();
		 System.out.println("Tiempo de ejecucion: "+ (( time_end - time_start )*0.001) +" Second");
		 System.out.println("Costo: "+cost);
		 System.out.println("El resultado de la multiplicacion con es: "+result);
		 */
		 
		 cost=0;
		 System.out.println("\nMultipliacion con  SCHONHAGE STRASSEN****************************\n");
		 time_start = System.currentTimeMillis();	
		 arrayResult=SchonhageStrassen(arrayIntnumA,arrayIntnumB);
		 time_end = System.currentTimeMillis();
		 strResult=ArrayInToStr(arrayResult);
		 System.out.println("Tiempo de ejecucion: "+ (( time_end - time_start )*0.001) +" Second");
		 System.out.println("Costo: "+cost);
		 System.out.println("El resultado de la multiplicacion con es: "+strResult);
		 
		 
	 }
	
		 
	 ///Suma de arreglos indice por indice. 
	 public static int[] SumTwoValues(int[] numA, int[] numB){
		 
		 
		 int[] bignum;
		 int[] lownum;
		 int j;
		 int k;
		 int i;		 
		 
		 if(isLongest(numA,numB)){
			bignum=numA;
			lownum=numB;
		 }
		 else{
			bignum=numB;
			lownum=numA;
		 }
		 
		 int[] intResult= new int[bignum.length + 1];
		 
		 i=intResult.length-1;
		 j=bignum.length-1;
		 k=lownum.length-1;				 
		 
		 while(i>0){
			 if(j>=0)
				 intResult[i]+=bignum[j];
			 
			 if(k>=0)
				 intResult[i]+=lownum[k];
			 
			 
			 if(intResult[i]>=10){
				 intResult[i]-=10;
				 intResult[i-1]+=1; 
			 }
				 
			 
			 if(i>=0)	 
			 i--;
			 
			 if(j>=0)
			 j--;
			 
			 if(k>=0)
			 k--;
			 
		 }	 
		
		
		 return intResult;
	 }
	 	
	 ///determina si el arreglo es mas grande que el otro deacuerdo al indice
	 private static Boolean isLongest(int[] numlong, int[] numlow){
		 
		 int indexlong=numlong.length, indexlow=numlow.length;
		 int i=0;
		 
		 if(indexlong>indexlow)
			 return true;
		 
		 if(indexlong<indexlow)
			 return false;
		 
		 while(i<indexlong){
			 if(numlong[i]>numlow[i])
				 return true;
			 
			 if(numlong[i]<numlow[i])
				 return false;
			 
			 i++;
		 }
		 
		 return true;
	 }
	 
	 //multipliacion de dos numeros***
	 //El arreglo se suma continuamente en si mismo hasta llegar al 
	 //la cantidada de vece que tiene el Big Integer "multi" 
	 public static int[] OverLodadMulti(int[] intnum, int[] multi){
		 		 
		 int extraindex = 1*multi.length;		 		 
		 int[]intresult = new int[(intnum.length)+(extraindex-1)];
		 int j;
		 
		 while(IsZero(multi)!=true){
			 
			 j = intnum.length-1;
			 
			 for(int i=intresult.length-1; i>0; i--){
				 if(j>=0){
					 intresult[i]+= intnum[j];
					 cost++;
				 }
				 
				 if(intresult[i]>=10){
					 intresult[i]-=10;
					 intresult[i-1]+=1;
					 cost++;
				 }
				 
				 if(j>=0)	 
				 j--;
			 }
			 
			 multi=Descrement(multi);
		 }
		
		
		 return intresult;
	 }
	 
	 //multiplica dos numero usando el algotimo de multipliacion de karatsuba
	 //Por el momento este algoritmo solo funciona con arreglos de como maximo
	 //4 posiciones.
	 public static int KaratsubaMulti(int[]x, int[]y){
		
		 int len_x = x.length;
		 int len_y = y.length;
			
		 int half_x = (int) Math.ceil(len_x / 2);
		 int half_y = (int) Math.ceil(len_y / 2);
		 int base = 10;
		 cost++;
		 
		// Boton de la recursion.
			
		 if (len_x == 1 && len_y == 1){
			 cost++;	
			 return x[0] * y[0];
		 }
		 
		 int[][] x_chunks = chunkArray(x, half_x);
		 int[][] y_chunks = chunkArray(y, half_y);
		 
		// Alianza predefinida.
			
		 int[] x1 = x_chunks[0];			
		 int[] x2 = x_chunks[1];			
		 int[] y1 = y_chunks[0];		
		 int[] y2 = y_chunks[1];
		 
		 
			return  KaratsubaMulti(x1, y1) * (int)Math.pow(base, half_x * 2) 							// a
				 	+ (KaratsubaMulti(x1, y2) + KaratsubaMulti(x2, y1)) * (int)Math.pow(base, half_x) 	// b
				 	+ KaratsubaMulti(x2, y2);															// c
			
	 }
	
	 //resta en el ultimo valor del arreglo y lo actualiza el arreglo con los 
	 //valores correspondiente al Big Integer
	 private static int[] Descrement(int[] num){	 
		 
		 if(IsZero(num)!=true){
			 num[num.length-1]--;
		 
			 for(int i=0; i<num.length; i++){
	 
				 if(num[i]<0){
					 num[i]=9;
					 num[i-1]-=1;
					 i=-1;
				 }
			 }  
		 }
			 
		 return num;
	 }
	  
	 ///verificar si un arreglo esta completamente en con ceros 
	 private static Boolean IsZero(int[]num){
		 
		for(int i=0; i<num.length; i++){
			 if(num[i]>0)
				 return false;
		}
		 
		 return true;
	 }
	 
	 //Metodo para dividir una matriz en segmentos de igual tama�o donde el cual 
	 //el �ltimo trozo puede ser m�s peque�o que el resto.
	 //credits: https://gist.github.com/lesleh/7724554
	 public static int[][] chunkArray(int[] array, int chunkSize) {
	        int numOfChunks = (int)Math.ceil((double)array.length / chunkSize);
	        int[][] output = new int[numOfChunks][];

	        for(int i = 0; i < numOfChunks; ++i) {
	            int start = i * chunkSize;
	            int length = Math.min(array.length - start, chunkSize);

	            int[] temp = new int[length];
	            System.arraycopy(array, start, temp, 0, length);
	            output[i] = temp;
	        }

	        return output;
	    }
		
	 //recibe dos arreglos y si uno de ellos es mas peque�o
	 //que el otro, entonces al peque�o lo devuelve del mismo 
	 //tama�o que el otro mas grande
	 //y lo vuelve par e comparacion al otro arreglo.
	 static int[] PutEqualAndEvenIndex(int[] check, int indextocompare){
	 	 
		 int[] result ;	 
		 int dif;	 		
	 	 int i;
	 	 
		 if(check.length%2==0 && check.length>=indextocompare)
			 return check;
		 
		 
		 if(check.length<indextocompare){
			 result = new int[indextocompare];
			 dif = indextocompare-check.length;
			 
			 i=0;
			 while(i<check.length)	 
				 result[dif++]=check[i++];
			 
			 return PutEven(result,indextocompare);
		 }
		 
		 
	
		 return PutEven(check,indextocompare);	
	 }
	 
	 //Si un arreglo es de indice impar con respecto al otro entonces 
	 //se retorna el mimso numero con un cero por delante(pone el indice par)
	 static int[] PutEven(int[] check, int indextocompare){
		 
		 if(check.length%2==0 && check.length >= indextocompare)
			 return check;
		 
		 
		 int[] result = new int[check.length+1];	 
		 		 
		 int i=1;
		 int j=0;
		 while(i<result.length)			 
			 result[i++]=check[j++];
			 
		 
		 return result; 
	 }
	 
	//Multiplicacion Schonhage Strassen
	 static public int[] SchonhageStrassen(int[] numA, int[] numB){
	
		 if(numA.length==1)		 	 
			 return OverLodadMulti(numB,numA);
		 
		 if(numB.length==1)
			 return OverLodadMulti(numA,numB);
		 
		int[] intbig;
		int[] intlow;
		int[][] multiDResult;
		
		if(numA.length>numB.length){
			intbig=numA;
			intlow=numB;
			
		}
		else{
			intbig=numB;
			intlow=numA;
		}
		
		multiDResult=new int[intlow.length][intbig.length];
		
		if(intbig.length==intlow.length){
			for(int i=intbig.length-1; i>=0; i--){				
				for(int j=intlow.length-1; j>=0; j--){					
					multiDResult[i][j]=intbig[i]*intlow[j];										
					cost++;				
				}			
			}
		}	
		else{
			for(int i=intbig.length-1; i>=0; i--){				
				for(int j=intlow.length-1; j>=0; j--){					
					multiDResult[j][i]=intbig[i]*intlow[j];										
					cost++;				
				}			
			}
		}
		
							 
		 return Convulacion(multiDResult);
	 }
	 
	 //devulve la convulacion del arreglo multimencional 
	 static int[]Convulacion(int[][] num){
		 
		 int dif=num.length-1;
		 int[] uniDimentResult= new int [num[0].length+dif];
		 int[][] multiDimentResult;
		 int k=0,r=0,m=0, n=0;
		 
		 while(k<=dif)
		 {
			 r+=k;
			 while(n<=num[0].length-1){
				 uniDimentResult[r++]+= num[m][n++];
				 cost++;
			 }
				 k++;
				 r=0;
				 m++;
				 n=0;
		 }
		 
		  multiDimentResult =UniDimenToMultiDimen(uniDimentResult);
		 	 
		return ConvulacionInver(multiDimentResult); 
	 }
	 
	 //hace la cobulacion sumando los numeros de derecha a izquierda
	 private static int[] ConvulacionInver(int[][] num){
	 
		 int dif=num.length-1;
		 int[] uniDimentResult= new int [dif+1];
		 int k=0,r=dif,m=dif, n=num[0].length-1;
		 
		 while(k<=dif)
		 {
			 r-=k;
			 while(n>=0){
				 if(r<0)
					 break;
				 
				 uniDimentResult[r--]+= num[m][n--];
				 cost++;
				 if(uniDimentResult[r+1]>=10){
					uniDimentResult[r+1]-=10;
					uniDimentResult[r]+=1;
					cost++;
				 } 							 
			 }				 
			 k++;				 
			 r=dif;				 
			 m--;				 
			 n=num[0].length-1;				 
		 }
		 
		 return uniDimentResult;
	 }
	 
	 //Convierte un arreglo unidimesional a un arreglo multidimensional
 	 private static int[][] UniDimenToMultiDimen(int[]uniDi){
		 
 		 int maxLenght=FindLenghtOfLargest(uniDi);
 		 
		 int[][] result= new int[uniDi.length][maxLenght];
		 	
		 int i=0,j=0,m=0,n=0;
		 int firt,second;
		 String num;
		 
		 while(m<result.length){
			 
			num=Integer.toString(uniDi[i]);
			 			 
			 result[m]=StrToArrayInt(num,maxLenght);
			 m++; 
			 i++;
			 n=0;
		 }
		
		 return result;
	 }
	 	
 	///devuelve la cantidad de digitos que tiene el numero mas grande en el arreglo
 	 private static int FindLenghtOfLargest(int[]num){
 		 
 		 String strnum;
         int smallest = num[0];
         int largetst = num[0];
        
         for(int i=1; i< num.length; i++)
         {
                 if(num[i] > largetst)
                         largetst = num[i];
                 else if (num[i] < smallest)
                         smallest = num[i];
                
         }
 		 
         strnum = Integer.toString(largetst);
 		 
 		 return strnum.length();
 	 }
 	 
 	///convierte un string a un arreglo entero
 		 private static int[] StrToArrayInt(String strnum,int lenght){
 		 
 			int[] arraynumber=new int[lenght];
 			 
 			 int j=strnum.length()-1,i=arraynumber.length-1;
 			 
 			 while(j>=0){
 				arraynumber[i]= Character.getNumericValue(strnum.charAt(j));
 				j--;
 				i--;
 			 }
 	 		
 	 			  
 			 return arraynumber;
 		 }
 		 
	 //convierte un Arreglo de enteros a un String
	 private static String ArrayInToStr(int[] num){
		 String strnum="";
		 
		 for(int i=0; i<num.length; i++){
			 strnum+=Integer.toString(num[i]);
		 }
		 		 
		 return strnum;
	 }
	

}
