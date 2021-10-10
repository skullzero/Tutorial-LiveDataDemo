### LiveData is an observable data holder class. 
+ LiveData is part of the Lifecycle library which was designed to help solve common Android Lifecycle challenges.
+ Unlike a regular observable, LiveData is **lifecycle-aware**, meaning it respects the lifecycle of other app components,such as activities, fragments, or services. 
This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

+ For example, if livedata in ViewModel was changed, only when activity is active status(on screen), including
    1. after onStart()
    2. after onResume()
    3. after onPause(), but before onStop()

### Reference
- https://developer.android.com/topic/libraries/architecture/livedata
