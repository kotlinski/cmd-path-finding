

package se.kotlinski.deprecated.controller.graph.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



public class FibonacciHeap<T>
{


    private static final double oneOverLogPhi =
        1.0 / Math.log((1.0 + Math.sqrt(5.0)) / 2.0);




    private FibonacciHeapNode<T> minNode;


    private int nNodes;




    public FibonacciHeap()
    {
    }




    public boolean isEmpty()
    {
        return minNode == null;
    }




    public void clear()
    {
        minNode = null;
        nNodes = 0;
    }




    public void decreaseKey(FibonacciHeapNode<T> x, double k)
    {
        if (k > x.key) {
            throw new IllegalArgumentException(
                "decreaseKey() got larger key value. Current key: "+x.key+" new key: "+k);
        }

        x.key = k;

        FibonacciHeapNode<T> y = x.parent;

        if ((y != null) && (x.key < y.key)) {
            cut(x, y);
            cascadingCut(y);
        }

        if (x.key < minNode.key) {
            minNode = x;
        }
    }




    public void delete(FibonacciHeapNode<T> x)
    {

        decreaseKey(x, Double.NEGATIVE_INFINITY);


        removeMin();
    }




    public void insert(FibonacciHeapNode<T> node, double key)
    {
        node.key = key;


        if (minNode != null) {
            node.left = minNode;
            node.right = minNode.right;
            minNode.right = node;
            node.right.left = node;

            if (key < minNode.key) {
                minNode = node;
            }
        } else {
            minNode = node;
        }

        nNodes++;
    }




    public FibonacciHeapNode<T> min()
    {
        return minNode;
    }




    public FibonacciHeapNode<T> removeMin()
    {
        FibonacciHeapNode<T> z = minNode;

        if (z != null) {
            int numKids = z.degree;
            FibonacciHeapNode<T> x = z.child;
            FibonacciHeapNode<T> tempRight;


            while (numKids > 0) {
                tempRight = x.right;


                x.left.right = x.right;
                x.right.left = x.left;


                x.left = minNode;
                x.right = minNode.right;
                minNode.right = x;
                x.right.left = x;


                x.parent = null;
                x = tempRight;
                numKids--;
            }


            z.left.right = z.right;
            z.right.left = z.left;

            if (z == z.right) {
                minNode = null;
            } else {
                minNode = z.right;
                consolidate();
            }


            nNodes--;
        }

        return z;
    }




    public int size()
    {
        return nNodes;
    }




    public static <T> FibonacciHeap<T> union(
        FibonacciHeap<T> h1,
        FibonacciHeap<T> h2)
    {
        FibonacciHeap<T> h = new FibonacciHeap<T>();

        if ((h1 != null) && (h2 != null)) {
            h.minNode = h1.minNode;

            if (h.minNode != null) {
                if (h2.minNode != null) {
                    h.minNode.right.left = h2.minNode.left;
                    h2.minNode.left.right = h.minNode.right;
                    h.minNode.right = h2.minNode;
                    h2.minNode.left = h.minNode;

                    if (h2.minNode.key < h1.minNode.key) {
                        h.minNode = h2.minNode;
                    }
                }
            } else {
                h.minNode = h2.minNode;
            }

            h.nNodes = h1.nNodes + h2.nNodes;
        }

        return h;
    }




    @Override public String toString()
    {
        if (minNode == null) {
            return "FibonacciHeap=[]";
        }


        Stack<FibonacciHeapNode<T>> stack = new Stack<FibonacciHeapNode<T>>();
        stack.push(minNode);

        StringBuffer buf = new StringBuffer(512);
        buf.append("FibonacciHeap=[");


        while (!stack.empty()) {
            FibonacciHeapNode<T> curr = stack.pop();
            buf.append(curr);
            buf.append(", ");

            if (curr.child != null) {
                stack.push(curr.child);
            }

            FibonacciHeapNode<T> start = curr;
            curr = curr.right;

            while (curr != start) {
                buf.append(curr);
                buf.append(", ");

                if (curr.child != null) {
                    stack.push(curr.child);
                }

                curr = curr.right;
            }
        }

        buf.append(']');

        return buf.toString();
    }




    protected void cascadingCut(FibonacciHeapNode<T> y)
    {
        FibonacciHeapNode<T> z = y.parent;


        if (z != null) {

            if (!y.mark) {
                y.mark = true;
            } else {

                cut(y, z);


                cascadingCut(z);
            }
        }
    }



    protected void consolidate()
    {
        int arraySize =
            ((int) Math.floor(Math.log(nNodes) * oneOverLogPhi)) + 1;

        List<FibonacciHeapNode<T>> array =
            new ArrayList<FibonacciHeapNode<T>>(arraySize);


        for (int i = 0; i < arraySize; i++) {
            array.add(null);
        }


        int numRoots = 0;
        FibonacciHeapNode<T> x = minNode;

        if (x != null) {
            numRoots++;
            x = x.right;

            while (x != minNode) {
                numRoots++;
                x = x.right;
            }
        }


        while (numRoots > 0) {

            int d = x.degree;
            FibonacciHeapNode<T> next = x.right;


            for (;;) {
                FibonacciHeapNode<T> y = array.get(d);
                if (y == null) {

                    break;
                }



                if (x.key > y.key) {
                    FibonacciHeapNode<T> temp = y;
                    y = x;
                    x = temp;
                }


                link(y, x);


                array.set(d, null);
                d++;
            }



            array.set(d, x);


            x = next;
            numRoots--;
        }



        minNode = null;

        for (int i = 0; i < arraySize; i++) {
            FibonacciHeapNode<T> y = array.get(i);
            if (y == null) {
                continue;
            }


            if (minNode != null) {

                y.left.right = y.right;
                y.right.left = y.left;


                y.left = minNode;
                y.right = minNode.right;
                minNode.right = y;
                y.right.left = y;


                if (y.key < minNode.key) {
                    minNode = y;
                }
            } else {
                minNode = y;
            }
        }
    }




    protected void cut(FibonacciHeapNode<T> x, FibonacciHeapNode<T> y)
    {

        x.left.right = x.right;
        x.right.left = x.left;
        y.degree--;


        if (y.child == x) {
            y.child = x.right;
        }

        if (y.degree == 0) {
            y.child = null;
        }


        x.left = minNode;
        x.right = minNode.right;
        minNode.right = x;
        x.right.left = x;


        x.parent = null;


        x.mark = false;
    }




    protected void link(FibonacciHeapNode<T> y, FibonacciHeapNode<T> x)
    {

        y.left.right = y.right;
        y.right.left = y.left;


        y.parent = x;

        if (x.child == null) {
            x.child = y;
            y.right = y;
            y.left = y;
        } else {
            y.left = x.child;
            y.right = x.child.right;
            x.child.right = y;
            y.right.left = y;
        }


        x.degree++;


        y.mark = false;
    }


}


