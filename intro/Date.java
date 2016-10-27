package edu.ufp.inf.lp2.intro;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements java.io.Serializable{

  private int day;

  private int month;

  private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date() {
      Calendar gregCalendar = new GregorianCalendar();
      this.setDay(gregCalendar.get(Calendar.DAY_OF_MONTH));
      this.setMonth(gregCalendar.get(Calendar.MONTH)+1);// +1 porque JANEIRO = mes '0'
      this.setYear(gregCalendar.get(Calendar.YEAR));
    }
   
    public int differenceYears(Date d) {
        
        if(this.getYear()==d.getYear()){
            return 0;  
        }
        else if(this.getYear()>d.getYear()){
            return this.getYear()-d.getYear();
        }
        else if(d.getYear()>this.getYear()){
            return d.getYear()-this.getYear();
        } 
        return -1;
    }
    
   
    public int differenceMonths(Date d) {
        // verificar a difrenca de anos entre as duas datas
        int difYears = (this.differenceYears(d));
        
        // se forem do mesmo ano, subtrair os meses
        if (difYears == 0) {
            return Math.abs(d.month - this.month);
            
        // case sejam de anos diferentes, calcular a diferenca de meses *(vezes) o numero de anos de diferenca.
        } else if (difYears >= 1) {
            if (this.beforeDate(d)) {
                return (d.month + (12 - this.month)) * difYears;
            } else {
                return (this.month + (12 - d.month)) * difYears;
            }
        }
        return -1;
    }
    

    
    public boolean afterDate(Date d){
        
        if(this.year==d.year&&this.month==d.month && this.day==d.day){
            return false;
        }else{
            return !beforeDate(d);
        }
        
    }
   
    public boolean beforeDate(Date d) {
        if ( this.getYear() < d.getYear()) {
            
            return true;// this é anterior a d
            
        } else if (this.getYear() == d.getYear()) {

            if (this.getMonth() < d.getMonth()) {
                
                return true;// this é anterior a d

            } else if (this.getMonth() == d.month) {
                
                return this.getDay() < d.getDay(); // true caso this.day < d.day 
            }
        }// o ano do objecto d é menor
        return false;
    }
    /*
    public void incrementDate(Date d){
        if(this.month==12){
            if(this.day==daysMonth(this.month,this.year)){
                daysMonth(1,this.year)-
            }
        }
    }
    */
   
    public int daysMonth(int month, int year){
       
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:return 31;
                
            case 2: return isLeapYear(year)?29:28;
                
            case 4:
            case 6:
            case 9:
            case 11: return 30;
            
            default: System.out.println("mes errado"); break;
                
        }
      return -1;

    }
   
    public boolean isLeapYear(int ano) {

        if (ano % 4 == 0 && (ano % 400 == 0 || ano % 100 != 0)) {
            System.out.println(ano + " é Ano bissexto");
            return true;

        } else {
            System.out.println(ano + "Ano nao bissexto");
            return false;
        } 
        
        
    }
    @Override
    public String toString() {
        return "Date{" + "day=" + day + ", month=" + month + ", year=" + year + '}';
    }
    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    public int differenceDays(Date dataFim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}