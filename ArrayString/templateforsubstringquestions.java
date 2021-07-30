// // Here comes the template. (For questions where we have to find the substring in the array with some conditions )

// // For most substring problem, we are given a string and need to find a substring of it which
// // satisfy some restrictions. A general way is to use a hashmap assisted with two pointers.
// // The template is given below.

public int findSubstring(String s)
{
    int[] freq = new int[128];
    int counter;            // check whether the substring is valid
    int si = 0, ei = 0;     //two pointers, si(starting index) and ei(ending index) of window
    int d;                  //the length of substring

    for ()
    { /* initialize the hash map or frequency array here */
    }

    while (ei < s.length())
    {
            if(freq[s.charAt(ei++)]-- ?)
            { /* modify counter here */
            }

            while (/* counter condition */)
            {

                /* update d here if finding minimum, window(ei-si) */

                //increase  si  to make it invalid/valid again
                if(freq[s.charAt(si++)]++ ?)
                { /*modify counter here*/
                }
            }
             
            /* update d here if finding maximum, window(ei-si) */
    }
    return d;
}

// // One thing needs to be mentioned is that when asked to find maximum substring,
// // we should update maximum after the inner while loop to guarantee that the substring is valid.

// // On the other hand, when asked to find minimum substring, we should update minimum inside
// // the inner while loop.


