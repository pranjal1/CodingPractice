import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { UploadService } from './upload-file.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // required for input file change detection
    ReactiveFormsModule,
    HttpClientModule, // this is required for the actual http request
  ],
  providers: [UploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
