import React from 'react';
 
import { NavLink } from 'react-router-dom';
 
const NavFooter = () => {
    return (
       <div>
          <ul>
             <li>
          <NavLink to="/About">About Us///</NavLink>
          </li>
          <li>
          <NavLink to="/Howto">\\\How To</NavLink>
          </li>

          </ul>
       </div>
    );
}
 
export default NavFooter;