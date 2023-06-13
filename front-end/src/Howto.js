import React from 'react';
 
const Howto = () => {
    return (
      <div>
         <ul>
          <h1>How to Use</h1>
          <p>Τί να επιλέξετε στο κάθε κομβίο ανάλογα με το τί θέλετε να αναζητήσετε:</p>

    

          <li>
               <h1>Datasets:</h1> 
               <p>Επιλέξτε τι είδους δεδομένα θέλετε να προσπελάσετε. Υπάρχουν τέσσερις (4) πίνακες δεδομένων.</p>
               <tr>Actual Total Load</tr>
               <tr>Aggregated Generation Per Type</tr>
               <tr>Day Ahead Total Load Forecast</tr>
               <tr>Actual vs Forecast</tr>
             </li>

             <li>
               <h1>Area Name:</h1> Επιλέξτε την περιοχή αναζήτησης
             </li>

         <li>
               <h1>Production Types:</h1> 
               Επιλέξτε το είδος της ενέργειας για το οποίο επιθυμείτε να δείτε δεδομένα. Αν επιθυμείτε να αναζητήσετε για όλους τους διαθέσιμους τύπους ενέργειας, πληκτρολογήστε "ΑllΤypes".
               <tr>Oι διαθέσιμοι τύποι ενέργειας είναι:</tr>
                  <tr>Fossil Gas</tr>
                  <tr>Hydro Run-of-river and poundage</tr>
                  <tr>Hydro Pumped Storage</tr>
                  <tr>Hydro Water Reservoir</tr>
                  <tr>Fossil Hard coal</tr>
                  <tr>Nuclear</tr>
                  <tr>Fossil Brown coal/Lignite</tr>
                  <tr>Fossil Oil</tr>
    <tr>Fossil Oil shale</tr>
    <tr>Biomass</tr>
    <tr>Fossil Peat</tr>
   <tr>Wind Onshore</tr>
   <tr>Other</tr>
   <tr>Wind Offshore</tr>
   <tr>Fossil Coal-derived gas</tr>
   <tr>Waste</tr>
   <tr>Solar</tr>
   <tr>Geothermal</tr>
   <tr>Other renewable</tr>
   <tr>Marine</tr>
   <tr>AC Link</tr>
   <tr>Transformer</tr>
   <tr>DC Link</tr>
   <tr>Substation</tr>
    
   </li>

             <li>
                <h1>Resolution Code: </h1>
                <tr>Αν επιθυμείτε χρονική ανάλυση των δεδομένων ανά 60 λετπά, πληκτρολογήστε PT60M. </tr>
                <tr>Αν επιθυμείτε χρονική ανάλυση των δεδομένων ανά 30 λετπά, πληκτρολογήστε PT30M. </tr>
                <tr>Αν επιθυμείτε χρονική ανάλυση των δεδομένων ανά 15 λετπά, πληκτρολογήστε PT15M. </tr>
             </li>

             <li>
                <h1>Στην συνέχεια εισάγετε την ημερομηνία:</h1> 
                <tr>Αν επιθυμείτε αποτελέσματα ενός μήνα, αφήστε κενό το κουτί συμπλήρωσης της ημέρας. </tr>
                <tr>Αντίστοιχα αν επιθυμείτε αποτελέσματα ενός χρόνου, αφήστε κενό τα κουτιά συμπλήρωσης της ημέρας και του μήνα.</tr>
             </li>
             <li>
                Στην περίπτωση που κάποιο από τα στοιχεία δεν είναι σωστά ή δεν υπάρχει καταχωρημένο, δεν θα εμφανιστεί κάποιο αποτέλεσμα.
             </li>
      </ul>
      </div>
     
    );
}
 
export default Howto;