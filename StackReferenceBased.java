public class StackReferenceBased 
                  implements StackInterface 
{
  private Node top;

  public StackReferenceBased() 
  {
    top = null;
  } 
  /** Determines whether the stack is empty.
      @return true if the stack is empty, otherwise returns false.
  */
  public boolean isEmpty() 
  {
    return top ==  null;
  }  
  /** Adds an item to the top of a stack.
      @ param newItem is the item to be added.
  */
  public void push(Object newItem) throws StackException
  {
    top = new Node(newItem, top);

  } 
  /** Removes the top of a stack.
      @return the item that was added most recently is removed 
      from thestack and returned.
      @throws StackException if the stack is empty.
  */
  public Object pop() throws StackException 
  {
    if (!isEmpty()) {
      Node temp = top;
      top = top.getNext();
      return temp.getItem();
    }
    else {
      throw new StackException("StackException on " +
                               "pop: stack empty");
    }  
  }  
  /** remove all items from stack
  */
  
  public void popAll() 
  {
    top = null;
  }
  /** Retrieves the top of a stack.
      @return the item that was added most recently   
      @throws StackException if the stack is empty.
  */
  public Object peek() throws StackException 
  {
    if (!isEmpty()) {
      return top.getItem();
    }
    else {
      throw new StackException("StackException on " +
                               "peek: stack empty");
    }  
  }  
}  
