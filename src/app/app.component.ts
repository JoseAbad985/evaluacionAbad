import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DukesServiceService, Candidato } from './services/dukes-service.service';

@Component({
  selector: 'app-root',
  standalone: true,
  styleUrls: ['./app.component.scss'],
  imports: [CommonModule, FormsModule],
  template: `
    <h1>Buscar Candidato por Lista</h1>
    <input type="text" [(ngModel)]="searchCedula" placeholder="Ingrese la Lista" />
    <button (click)="searchCandidato()">Buscar</button>
    <button (click)="showAllCandidatos()">Mostrar Todos</button>
    
    <div *ngIf="filteredClient">
      <h2>Candidato Encontrado:</h2>
      <p>{{ filteredClient.nombre }} - Lista: {{ filteredClient.lista }} - Binomio: {{ filteredClient.Binomio }}</p>
    </div>
    <div *ngIf="searchCedula && !filteredClient">
      <p>No hay candidato de la lista: {{ searchCedula }}</p>
    </div>

    <div *ngIf="showAll">
      <h2>Todos los Candidatos:</h2>
      <ul>
        <li *ngFor="let client of clients">{{ client.nombre }} - Lista: {{ client.lista }} - Binomio: {{ client.Binomio }}</li>
      </ul>
    </div>

    <h2>Agregar Nuevo Candidato</h2>
    <form (ngSubmit)="addCandidato()">
      <input type="text" [(ngModel)]="newCandidato.lista" name="lista" placeholder="Lista" required />
      <input type="text" [(ngModel)]="newCandidato.nombre" name="nombre" placeholder="Nombre" required />
      <input type="text" [(ngModel)]="newCandidato.edad" name="edad" placeholder="Edad" required />
      <input type="text" [(ngModel)]="newCandidato.ciudad" name="ciudad" placeholder="Ciudad" required />
      <input type="text" [(ngModel)]="newCandidato.u_titulo_reg" name="u_titulo_reg" placeholder="Título Registrado" required />
      <input type="text" [(ngModel)]="newCandidato.actividad" name="actividad" placeholder="Actividad" required />
      <input type="text" [(ngModel)]="newCandidato.Partido" name="Partido" placeholder="Partido" required />
      <input type="text" [(ngModel)]="newCandidato.Binomio" name="Binomio" placeholder="Binomio" required />
      <button type="submit">Agregar Candidato</button>
    </form>
  `,
})
export class AppComponent implements OnInit {
  clients: Candidato[] = [];
  title = 'Proyecto10';
  searchCedula: string = '';
  filteredClient: Candidato | null = null;
  showAll: boolean = false;
  newCandidato: Candidato = { lista: '', nombre: '', edad: '', ciudad: '', u_titulo_reg: '', actividad: '', Partido: '', Binomio: '' };

  constructor(private dukesService: DukesServiceService) {}

  ngOnInit(): void {
    this.dukesService.getClients().subscribe({
      next: (data) => (this.clients = data),
      error: (err) => console.error('Error fetching clients:', err),
    });
  }

  searchCandidato(): void {
    this.filteredClient = this.clients.find(client => client.lista === this.searchCedula) || null;
    this.showAll = false;
  }

  showAllCandidatos(): void {
    this.showAll = true;
    this.filteredClient = null;
  }

  addCandidato(): void {
    this.clients.push({ ...this.newCandidato });
    this.newCandidato = { lista: '', nombre: '', edad: "", ciudad: '', u_titulo_reg: '', actividad: '', Partido: '', Binomio: '' };
  }
}
