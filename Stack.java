public class Stack {
    private int top;
    private Object[] elements;
    public Stack(int capacity) {
        elements=new Object[capacity];
        top=-1;
    }
    public boolean isFull(){
        return (top+1==elements.length);
    }
    public boolean isEmpty(){
        return (top==-1);
    }
    public void push(Object data){
        if (isFull()){
            System.out.println("Stack is full !!");
        }else {
            top++;
            elements[top]=data;

        }
    }
    public Object pop(){
        if(isEmpty()){
            System.out.println("Stack is empty !!");
            return null;
        }else {
            Object data =elements[top];
            top--;
            return data;
        }
    }
    public Object peek(){
        if (isEmpty()){
            System.out.println("Stack is empty !!");
            return null;
        }else {
            return elements[top];
        }
    }
    public int size(){
        return  top+1;
    }
}
