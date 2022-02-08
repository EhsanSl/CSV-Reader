
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {
    
    private String fileName; 
     
    public CSVReader(String fileName) {
        this.fileName = fileName ; 
    }

    public int numberOfRows(){
        try{
            //initialization
            int rows = 0 ; 
            File f = new File(fileName) ; 
            Scanner sc = new Scanner(f) ;
            //TRAVERSING THROUGH EACH LINE 
            while(sc.hasNextLine()){
                sc.nextLine() ; 
                rows++ ; 
            } 
            sc.close() ; 
            return rows ; 
        }//in case file was not found 
        catch(FileNotFoundException exception){
            System.out.println(exception.getMessage());
            return  0 ; 
        }
    } ;// Returns the number of lines in the CSV file

    public int numberOfFields(int row) {
        try{
            //initialization
            ArrayList<String> arr = new ArrayList<String>() ; 
            int rowCount = 0 ; 
            int cols = 0 ;
            Boolean flag = false ;  
            char temp ;  
            String tempString = "" ; 

            File f = new File(fileName) ; 
            Scanner sc = new Scanner(f) ; 
            sc.useDelimiter("") ; 
            //finding the row of interest 
            while ( rowCount < row ){
                sc.nextLine() ; 
                rowCount++ ; 
            }
            //traversing through the Row of intrest  
            while(sc.hasNext()){
                String data = sc.next() ; 
                temp = data.charAt(0) ;  
                if ( temp == '"'){
                    flag = !flag ; 
                }
                if ( temp == ',' && flag == false){ // in case we had ',' and it was not enclosed 
                    arr.add(tempString) ; 
                    tempString = "" ; 
                    continue ; 
                }
                if( temp == '\n'){ // in case the line is over 
                    break ; 
                }
                tempString += temp ; 
                 
            }
            if ( !(tempString == "")){ // the last word from the row 
                arr.add(tempString) ; 
            }
            cols = arr.size(); 
            sc.close() ; 
            return cols ; 
        }//in case file was not found
        catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
            return  0 ;   
        }
        
    } ;// Returns the number of fields in a particular row
    
    
    public String field(int row, int column){
        try{
            //initialization
            ArrayList<String> arr = new ArrayList<String>() ; 
            int rowCount = 0 ; 

            Boolean flag = false ;  
            char temp ;  
            String tempString = "" ; 

            File f = new File(fileName) ; 
            Scanner sc = new Scanner(f) ; 
            sc.useDelimiter("") ; 
            //finding the row of interest 
            while ( rowCount < row && sc.hasNextLine()){
                sc.nextLine() ; 
                rowCount++ ; 
            }
            //traversing through the Row of intrest  
            while(sc.hasNext()){
                String data = sc.next() ; 
                temp = data.charAt(0) ;  
                if ( temp == '"'){
                    flag = !flag ; 
                }
                if ( temp == ',' && flag == false){ // in case we had ',' and it was not enclosed 
                    arr.add(tempString) ; 
                    tempString = "" ; 
                    continue ; 
                }
                
                if( temp == '\n'){ // in case the line is over 
                    break ; 
                }
                if ( temp == ','){
                    continue ; 
                }
                tempString += temp ; 
                 
            }
            if ( !(tempString == "")){ // the last word from the row 
                arr.add(tempString) ; 
            }
            sc.close(); 
            return arr.get(column) ; 
            

        }
        catch(FileNotFoundException exception){
            System.out.println(exception.getMessage());
            return null ; 
        }
        catch( IndexOutOfBoundsException exception){
            System.out.println(exception.getMessage());
            return null ; 
        }

    }; // Returns the field in a particular row and column


}
