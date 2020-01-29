import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private productName: string;

  constructor(private router: Router) { }

  ngOnInit() {
    // $(document).on('click','.navbar-collapse.in',function(e) {
    //   if( $(e.target).is('a:not(".dropdown-toggle")') ) {
    //       $(this).collapse('hide');
    //   }
    // });  
  }	

  toSearch(event){
    this.productName = event.target.value;
    this.router.navigate(['./search',{name:this.productName}]);
  }

}
