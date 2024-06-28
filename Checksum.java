package checksum;
import java.lang.*;
import java.util.*;

public class Checksum 
{
    public static void main(String[] args) 
    {
        System.out.println("Sender side: ");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of frame: ");
        int frame=sc.nextInt();
        System.out.println("Enter the number of bits in a frame: ");
        int bits=sc.nextInt();
        String frames[]=new String[frame];
        System.out.println("Enter the "+bits+"-bit of frames: ");
        for(int i=0; i<frame; i++)
            frames[i]=sc.next();
        
        String partialSum="";
        partialSum=Checksum_calci(frames[0],frames[1],bits);
        for(int i=2; i<frame; i++)
        {
            partialSum=Checksum_calci(partialSum,frames[i],bits);
        }
        String sent_checksum=complement(partialSum);
        int sent_sum=Integer.parseInt(sent_checksum,2);
        
        System.out.println("Receiver Side: ");
        String rec_frames[]=new String[frame];
        System.out.println("Enter the "+bits+"-bit of receiver side frames: ");
        for(int i=0; i<frame; i++)
            rec_frames[i]=sc.next();
        String rec_partialSum="";
        rec_partialSum=Checksum_calci(rec_frames[0],rec_frames[1],bits);
        for(int i=2; i<frame; i++)
        {
            rec_partialSum=Checksum_calci(rec_partialSum,rec_frames[i],bits);
        }
        int rec_sum=Integer.parseInt(rec_partialSum, 2);
        int sum=sent_sum+rec_sum;
        String sum1=Integer.toBinaryString(sum);
        String rec_checksum=complement(sum1);
        int rec_sum1=Integer.parseInt(rec_checksum, 2);
        
        if(rec_sum1==0)
            System.out.println("No Error");
        else
            System.out.println("Non Zero Checksum and Error");
    }
    public static String Checksum_calci(String frame1,String frame2,int bits)
    {
        int f1=Integer.parseInt(frame1, 2);
        int f2=Integer.parseInt(frame2, 2);
        int res=f1+f2;
        String result=Integer.toBinaryString(res);
        String result1="";
        int res1=0;
        if(result.length()>bits)
        {
            result1=result.substring(1, bits);
            int r=Integer.parseInt(result1);
            res1=r+1;
        }
        String sum=Integer.toBinaryString(res1);
        return sum;
    }
    public static String complement(String a)
    {
        String result = "";
        for(int i=0; i<a.length(); i++)
        {
            if(a.charAt(i)=='0')
                result+="1";
            else if(a.charAt(i)=='1')
                result+="0";
        }
        return result;
    }
}
