// BIT OPERATION

 * RIGHT SHIFT:   (computer me ye sign > right me hota ha isliye isko right shift bolte ha)
=> Right shift by one is equivalent to divide the number by 2, regardless of it being even or odd.
  
  For example,
  25 = 11001, 24 = 11000
  25/2 = 12 and 24/2 = 12 are both 1100

// NOTE : RIGHT SHIFT (more formally)

       (x >> number)  ===  (x)/(2^number)     ->   because haam right me shift kar rahe ha bit ko thats why it reduces the number by 2^number 

//Eg.  (right shift by 3) i.e  x >>3   means (divide x by 2^3) i.e  x / 2^3

//============================================================================================================



 * LEFT SHIFT:   (computer me ye sign > right me hota ha isliye isko right shift bolte ha)
=> Right shift by one is equivalent to multiplying the number by 2, and yaha pe number even ho ya odd faraq ni padta as haam to multiply kar rage ha 
  
  For example,
  25 = 11001  =>  25*2 = 50(10010)
  24 = 11000  =>  24*2 = 48(10000) 


// NOTE : LEFT SHIFT (more formally)

       (x << number)  ===  (x)*(2^number)     ->   because haam left me shift kar rahe ha bit ko thats why it the number increase by 2^number 

//Eg.  (left shift by 3) i.e  x <<3   means (multiply x by 2^3) i.e  x * 2^3