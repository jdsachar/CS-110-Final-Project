public interface StackInterface  {
  /** Determines whether the stack is empty.
      @return true if the stack is empty, otherwise returns false.
  */
  public boolean isEmpty();
  /** Removes all the items from the stack.
  */
  public void popAll();

  /** Adds an item to the top of a stack.
      @ param newItem is the item to be added.
      @throws StackException when newItem cannot be placed on the stack.
  */
  public void push(Object newItem) throws StackException;
  /** Removes the top of a stack.
      @return the item that was added most recently is removed 
      from thestack and returned.
  */
  public Object pop() throws StackException;

  /** Retrieves the top of a stack.
      @return the item that was added most recently   
      @throws StackException if the stack is empty.
  */
  public Object peek() throws StackException;
}  
