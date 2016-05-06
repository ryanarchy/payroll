/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll;

import appdomain.Employee;
import appdomain.FiscalYear;
import appdomain.HourlyEmployee;
import appdomain.SalariedEmployee;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ryanarchy
 */
public class EmployeeTest
{
    
    public static void main(String[] args)
    {
        HourlyEmployee employeeH1 = new HourlyEmployee("Jeff", 35.00, 2015);
        SalariedEmployee employeeS1 = new SalariedEmployee("Sally", 75000.0);
        
        for (HashMap<Date,Double> period : employeeH1.getFiscalYear(2015).getPayPeriods())
        {
            for (Map.Entry<Date,Double> day : period.entrySet())
            {
                day.setValue(15.1);
            }
        }
        
        for (LinkedHashMap<Date,Double> period : employeeS1.getFiscalYear(2016).getPayPeriods())
        {
            for (Map.Entry<Date,Double> day : period.entrySet())
            {
                day.setValue(15.0);
            }
        }
        
        employeeH1.addFiscalYearAfter();
        for (int fy : employeeH1.getFiscalYears())
        {
            System.out.println("Employee 1 Fiscal Year " + fy + ": " + employeeH1.getFiscalYear(fy));
        }
        employeeS1.addFiscalYearAfter();
        for (int fy : employeeS1.getFiscalYears())
        {
            System.out.println("Employee 2 Fiscal Year " + fy + ": " + employeeS1.getFiscalYear(fy));
        }
        System.out.println("Employee 1 (2015):");
        System.out.println("Gross Pay: " + employeeH1.getGrossPay(2015));
        System.out.println("Net pay: " + employeeH1.getNetPay(2015));
        System.out.println("SS Tax: " + employeeH1.getSocialSecurityTax(2015));
        System.out.println("Medicare Tax: " + employeeH1.getMedicareTax(2015));
        System.out.println("State Tax: " + employeeH1.getStateTax(2015));
        System.out.println("Federal Tax: " + employeeH1.getFederalTax(2015));
        System.out.println("Total Deductions: " + employeeH1.getDeductions(2015));
        System.out.println("Employee 2 (2015):");
        System.out.println("Gross Pay: " + employeeS1.getGrossPay(2015));
        System.out.println("Net pay: " + employeeS1.getNetPay(2015));
        System.out.println("SS Tax: " + employeeS1.getSocialSecurityTax(2015));
        System.out.println("Medicare Tax: " + employeeS1.getMedicareTax(2015));
        System.out.println("State Tax: " + employeeS1.getStateTax(2015));
        System.out.println("Federal Tax: " + employeeS1.getMedicareTax(2015));
        System.out.println("Total Deductions: " + employeeS1.getDeductions(2015));
        
    }
}
