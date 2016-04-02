

package se.kotlinski.graph.util;


public class FibonacciHeapNode<T>
{



    T data;


    FibonacciHeapNode<T> child;


    FibonacciHeapNode<T> left;


    FibonacciHeapNode<T> parent;


    FibonacciHeapNode<T> right;


    boolean mark;


    double key;


    int degree;




    public FibonacciHeapNode(T data)
    {
        right = this;
        left = this;
        this.data = data;
    }




    public final double getKey()
    {
        return key;
    }


    public final T getData()
    {
        return data;
    }


    @Override public String toString()
    {
        return Double.toString(key);
    }


}


