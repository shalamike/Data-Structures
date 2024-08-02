package recursion;

public class RecursiveMethods {

    /*
    Recursion is the process of a method calling itself from within a method as shown in the example below:

    public void someMethod(int parameter){
        method(parameter)
    }

    However, if this method is run, it will run indefinitely. as the exeution of the code when called will look
    something like this if we put in a number like 10:


    (note each arrow represents the return vale of each method after the initial call)
    initial method
         call
          |
          V
    someMethod(10) -> someMethod(10) -> someMethod(10) -> someMethod(10) -> someMethod(10) ....


    Note that when the method is called, it returns the exact same method call with the exact same parameter.
    Unless there is a break statement within the recursive method, it will run inidefinitely.
    therefore in order for the recursive method to finally end its termination, a termination (or break)
    statement must be introduced as well as some change to the parameter given to us as demonstrated below:

    public void someMethod(int parameter){
    if (n!= 0)
        method(parameter - 1);
    }

    where in this case, the termination statement happens on line 25 "if (n!= 0) and the change to our parameter
    happens on line 26 "method(parameter - 1)" where in this case, every time the method is called again, the parameter
    is reduced by a value of one. therefore when we run our new recursive method, it will constantly count backwards
    from our initial input number until it reaches 0 as shown below

        initial method                                                                                  end statemt
         call                                                                                               |
          |                                                                                                 |
          V                                                                                                 V
        someMethod(10) -> someMethod(9) -> someMethod(8) -> someMethod(7) -> someMethod(6) ->... -> someMethod(0)

      when methods are imlemented correctly, a way to think of their execution is that the act similarly to an
      elastic band, where each recursive call continuously pulls and pulls untill the last execution statement is
      reached. where in that case the elastic band is finally releasted

     */

    public void countBackRecursively(int n){
        if (n != 0) {
            System.out.println(n);
            countBackRecursively(n-1);
        }

    }

    public String countBackRecursivelyAsString(int n){
        if (n!=0){
            return n + " " + countBackRecursivelyAsString(n-1);
        }
        return "";
    }

    public void countForewardFromNum(int start, int finish){
        if (start != finish){
            System.out.println(start);
            countForewardFromNum(start + 1, finish);
        }
    }

    public String countForewardFromNumAsString(int start, int finish){
        if (start != finish){
            return start + " " + countForewardFromNumAsString(start + 1, finish);
        }
        return "";
    }

    public void revMessage(String str) {
        int lastIndex = str.length()-1;
        if (str.length() != 0){
            System.out.println(str.charAt(lastIndex));
            revMessage(str.substring(0, lastIndex));
        }
    }

    public String revString (String str) {
        int lastIndex = str.length()-1;
        if (str.length() != 0){
//            System.out.println(str.charAt(lastIndex));
            return str.charAt(str.length()-1) + revString(str.substring(0,str.length()-1));
        } else {
            return "";
        }
    }


    public int fibonacci(int n){
        if (n<=1)
            return n;
        else
            return (fibonacci(n-1) + fibonacci(n-2));
    }
    public int sum(int n){
        if (n >=  1){
            return n + sum(n-1);
        }
        else
            return n;
    }



}
