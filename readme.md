### LiveData is an observable data holder class. 
+ LiveData is part of the Lifecycle library which was designed to help solve common Android Lifecycle challenges.
+ Unlike a regular observable, LiveData is _**lifecycle-aware**_, meaning it respects the lifecycle of other app components,such as activities, fragments, or services. 
This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

+ For example, if livedata in ViewModel was changed, only when activity is active status(on screen), including
    1. after onStart(), STARTED state
    2. after onResume(), RESUMED/RUNNING state
    3. after onPause(), but before onStop()

### LiveData in other layer
* LiveData is not designed to handle asynchronous streams of data. Even though you can use LiveData transformations and MediatorLiveData to achieve this. this approach has drawbacks: the capability to combine streams of data is very limited and all LiveData objects _**are observed on the main thread, this probably can block the main thread**_
* If we need to use LiveData in other layer, can just consider using _**kotlin flow and then converting them to LiveData in the ViewModel using asLiveData().**_ 

### Transformation LiveData
* If want to make changes to the value stored in a LiveData object before dispatching it to the observers, or you may need to return a different LiveData instance based on the value of another one. 

### Reference
- https://developer.android.com/topic/libraries/architecture/livedata
