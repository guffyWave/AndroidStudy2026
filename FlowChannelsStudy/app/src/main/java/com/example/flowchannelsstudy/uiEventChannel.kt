package com.example.flowchannelsstudy


//Use Case 1: ViewModel sending UI events to UI (One-time events)
//Common real-life scenario: Snackbar, Toast, Navigation, Dialog.
//
//Recommended replacement for LiveData<Event> or SharedFlow when you want strict ordering + backpressure.