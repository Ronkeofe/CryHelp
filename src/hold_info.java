/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asakpere
 */
//whole essence is just to convert the query result which
//is a two-dimensional array into class data structure
 class hold_info {
   String residence;
   String crime_type;

   
       // makes it easier to    
        public hold_info(String [] temp) {
            this.residence = temp[0];
            this.crime_type = temp[1];
        }
        @Override
        public String toString(){
            return "residence:"+residence+"; crime: "+crime_type;
        }
   } 
