/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdomain;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ryanarchy
 */
public class HourlyEmployee implements Employee
{
    HashMap<Short, FiscalYear> fyCalendar;  //hash map that has year, FiscalYear class pairs.  Year will signify the fiscal year represented for lookup purposes.
    
    String name;
    Double rate;
    Calendar cal = Calendar.getInstance();

    public HourlyEmployee(String name, Double rate)
    {
        this.name = name;
        this.rate = rate;
    }
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void setPayRate(double rate)
    {
        this.rate = rate;
    }

    @Override
    public double getPayRate()
    {        
        return this.rate;
    }

    @Override
    //return a pay period in the form of a hashmap of a (date, hour) pairs
    public HashMap<Date, Double> getPayPeriod(short year, short period) 
    {
        FiscalYear y = fyCalendar.get(year);
        return y.getPeriod(period);
    }

    @Override
    public boolean hasYear(short year)
    {
        return fyCalendar.containsKey(year);
    }

    @Override
    public double getHours(Date date)
    {
        cal.setTime(date);
        FiscalYear y = fyCalendar.get((short) cal.get(Calendar.YEAR));
        return y.getDateHours(date);
    }

    @Override
    public double getTotalHours(short year)
    {
        return fyCalendar.get(year).getTotalHours();
    }

    @Override
    public void setHoursWorked(Date date, double hours)
    {
        cal.setTime(date);
        fyCalendar.get((short) cal.get(Calendar.YEAR)).setDateHours(date, hours);
    }

    @Override
    public double getFederalTax(short year)
    {
        return this.getGrossPay(year) * .15;
    }

    @Override
    public double getStateTax(short year)
    {
        return this.getGrossPay(year) * .03;
    }

    @Override
    public double getMedicareTax(short year)
    {
        return this.getGrossPay(year) * .0145;
    }

    @Override
    public double getSocialSecurityTax(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDeductions(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getGrossPay(short year)
    {
        FiscalYear fy = fyCalendar.get((short) cal.get(Calendar.YEAR));
        return (fy.getTotalHours() * this.getPayRate());
    }

    @Override
    public double getNetPay(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FiscalYear getFiscalYear(short year)
    {
        return fyCalendar.get((short) cal.get(Calendar.YEAR));
    }
    
}
