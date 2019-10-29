/*
Name of student: Camryn Rogers
Class: CS 3345
Section: 003
Semester: Spring 2019
Project 3: LazyBinarySearchTree 
 */
package lazybinarysearchtree;

import java.util.*;

public class LazyBinarySearchTree {
    // Private nested TreeNode class
    private class TreeNode{
        int key;
        TreeNode leftChild, rightChild;
        boolean deleted = false;
        
        TreeNode(){}
        TreeNode(int key){this.key = key;}
    }
    
    private TreeNode root;
    int size = 0;
    
    LazyBinarySearchTree(){}
    
    // Insert new element to a leaf node
    public boolean insert(int key) throws IllegalArgumentException{
        // Check to see if int is in range 1-99
        if (key < 1 || key > 99){
            throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
        }
        else{
            
            TreeNode newNode = new TreeNode(key);
            TreeNode curr = root;
            TreeNode trail = null;
            
            while(curr != null){
                trail = curr;
                if(key < curr.key)
                    curr = curr.leftChild;
                else if(key > curr.key)
                    curr = curr.rightChild;
                else if(key == curr.key){
                    // If duplicate of deleted, undelete (logically insert, return true)
                    if(curr.deleted){
                        curr.deleted = false;
                        return false;
                    }    
                    // If duplicate of undeleted, do nothing
                }
            }
            // Add to tree physically (add 1 to size, return false)
            if(size == 0)
                root = newNode;
            else if(key < trail.key)
                trail.leftChild = newNode;
            else if(key > trail.key)
                trail.rightChild = newNode;
            size++;
            return true;
            
        }
    }
    
    public boolean delete(int key) throws IllegalArgumentException{
        TreeNode curr = root;

        // Check to see if int is in range 1-99
        if (key < 1 || key > 99){
            throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
        }
        
        // Get to the node to be deleted and if undeleted mark as deleted (return true)
        while(curr != null){
                if(key < curr.key)
                    curr = curr.leftChild;
                else if(key > curr.key)
                    curr = curr.rightChild;
                else if(key == curr.key){
                    // If undeleted, mark as deleted
                    if(!curr.deleted){
                        curr.deleted = true;
                        return true;
                    }    
                    // If duplicate of deleted, do nothing
                }
            }
        // Return false
        return false;
    }
    
    public int findMin(){
        TreeNode curr = root;
        int counter = 0;
        
        // Return -1 if size = 0 (no minimum)
        if(size == 0)
            return -1;
        
        // If size = 1, return root
        if(size == 1)
            return curr.key;
        
        // Else get smallest value
        while(curr.leftChild != null){
            curr = curr.leftChild;
            counter++;
        }
        
        // If that node is deleted, do same but one less time and check for deleted.
        // Repeat until the smallest node isn't deleted
        while(curr.deleted){
            curr = root;
            for(int i = 0; i < counter - 1; i++){
                curr = curr.leftChild;
            }
            counter--;
        }
        // Minimum found, return
        return curr.key;
    }
    
    public int findMax(){
        TreeNode curr = root;
        int counter = 0;
        
        // Return -1 if size = 0 (no maximum)
        if(size == 0)
            return -1;
        
        // If size = 1, return root
        if(size == 1)
            return curr.key;
        
        // Else get max value
        while(curr.rightChild != null){
            curr = curr.rightChild;
            counter++;
        }
        
        // If that node is deleted, do same but one less time and check for deleted.
        // Repeat until the max node isn't deleted
        while(curr.deleted){
            for(int i = 0; i < counter - 1; i++){
                curr = curr.rightChild;
            }
            counter--;
        }
        // Maximum found, return
        return curr.key;
    }
    
    public boolean contains(int key) throws IllegalArgumentException{
        TreeNode curr = root;
        
        // Check to see if int is in range 1-99
        if (key < 1 || key > 99){
            throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
        }

        // Return true if exists and non-deleted
        while(curr != null){
                if(key < curr.key)
                    curr = curr.leftChild;
                else if(key > curr.key)
                    curr = curr.rightChild;
                else if(key == curr.key){
                    // If found and not deleted, return true
                    if(!curr.deleted){
                        return true;
                    }    
                    // If deleted, return false
                    return false;
                }
            }
        // If not found, return false
        return false;
    }
    
    @Override
    public String toString(){
        
        TreeNode curr = root;

        // Print tree (including deleted with * right before) with a space in between
        // each integer, pre-order traversal
        
        String toreturn = "";
        
        // If empty, return an empty string
        if(curr == null)
            return "";
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(curr);
        
        while(!stack.empty()){
            TreeNode toprint = stack.peek();
            
            // If deleted, add asterisk
            if(toprint.deleted)
                toreturn += ("*" + toprint.key + " ");
            else
                toreturn += (toprint.key + " ");
            
            // Pop it
            stack.pop();
            
            // Push right/left children of popped to stack
            if(toprint.rightChild != null)
                stack.push(toprint.rightChild);
            if(toprint.leftChild != null)
                stack.push(toprint.leftChild);    
        }

        return toreturn;
    }
    
    public int height(){
        TreeNode curr = root;
        // Initialize to 1 to account for root
        int leftheight = 1, rightheight = 1;
        
        // Return height, including deleted elements
        
        if(size == 0)
            return 0;
        if(size == 1)
            return 1;
        
        // Check left subtree
        while(curr.leftChild != null){
            curr = curr.leftChild;
            leftheight++;
        }
        
        // Check right subree
        while(curr.rightChild != null){
            curr = curr.rightChild;
            rightheight++;
        }
        
        if(leftheight >= rightheight)
            return leftheight;
        else
            return rightheight;
    }
    
    public int size(){
        // Return size, including deleted elements
        return size;
        
    }
}
