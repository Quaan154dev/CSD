/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
//==================================================================
    void addLast(Corn x) {
        Node qNode = new Node(x);
        if (isEmpty()) {
            head = tail = qNode;
            return;
        }
        tail.next = qNode;
        tail = qNode;

    }
    void addLast(String xCode, int xType, int xPrice) {//You should write here appropriate statements to complete this function.
        if (xCode.charAt(0) == 'B') {
            return;
        } else {
            Corn corn = new Corn(xCode, xType, xPrice);
            addLast(corn);
        }
    }

    void f1() throws Exception {/* You do not need to edit this function. Your task is to complete the addLast  function
        above only.
         */
        clear();
        loadData(2);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
     void insertBefore(Node q, Corn x) {
        if (q == null) {
            return;
        }
        if (q == head) { // chen tuoc node Head
            // cap nhat lai head
            addFirst(x);
            return;
        }
        // tim node F truoc node q
        Node fNode = head;
        while (fNode != null && fNode.next != q) {
            fNode = fNode.next;
        }
        if (fNode == null) { // q khong co trong list
            return;

        }

        // insert after f
        insertAfter(fNode, x);

    }
     void addFirst(Corn x) {
        Node currentNode = new Node(x);
        head = new Node(x, head);
        if (tail == null) {
            tail = currentNode;
        }
    }

    // (10)
    void insertAfter(Node q, Corn x) {
        if (q == null) {
            return;

        }
        Node qNext = q.next;
        Node pNode = new Node(x, qNext);
        q.next = pNode;
        if (tail == q) { // insert sau Node tail
            // cap nhat lai tail
            tail = pNode;
        }

    }
    // (16) tim Node o vi tri thu k
    Node pos(int k) {
        int count = 0;
        Node pNode = head;
        while (pNode != null) {
            if (count == k) {
                return pNode;

            }
            count++;
            pNode = pNode.next;
        }

        return (null);
    }
    void f2() throws Exception {
        clear();
        loadData(6);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Corn x, y;
        x = new Corn("X", 1, 2);
        y = new Corn("Y", 3, 4);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //code space
        // chèn thằng y vào vtri thứ 2
        insertBefore(pos(2), x);
        // chèn y sau thằng x tức là vtri 3
        insertBefore(pos(3), y);
        //code space
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    public Node getNode(int i) {
        int count = 0;
        Node p = head;
        while (count < i) {
            count++;
            p = p.next;
        }
        return p;
    }

//==================================================================
    Node findMaxAge() {
        Node maxNode = this.head;
        Node currentNode = this.head;
        // loop from head to tail
        while (currentNode != null) {
            // if the age of Node have value higher than maxNode then assign maxNode = that
            // Node
            if (currentNode.info.type > maxNode.info.type) {
                maxNode = currentNode;
            }
            currentNode = currentNode.next;

        }
        return (maxNode);
    }

    void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }

    }

    void remove(Node q) {
        if (q == null) {
            return;
        }
        if (q == head) { // xoa not dau tien
            removeFirst();
            return;

        }
        // tim node F truoc node q
        Node fNode = head;
        while (fNode != null && fNode.next != q) {
            fNode = fNode.next;
        }
        if (fNode == null) { // q khong co trong list
            return;

        }
        // xoa q khoi list
        fNode.next = q.next;
        if (fNode.next == null) {
            tail = null;
        }

    }
   
    void f3() throws Exception {
        clear();
        loadData(10);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
       // code space
        Node p = findMaxAge();    
        if(p.next == null){
          return;
        }else{
           remove(p.next);
        }
       // code space

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    void sortByAge(int k, int h) {// sort từ vị trí nào đến vị trí nào thì dùng cái này
        if (k >= h) {
            return;
        }
        if (k < 0) {
            k = 0;
        }
        int n = size();
        if (h > n - 1) {
            h = n - 1;
        }
        Node start = pos(k);
        Node end = pos(h + 1);
        Node pi, pj;
        Corn temp;
        pi = start;
        while (pi != end) {
            pj = pi.next;
            while (pj != end) {
                if (pi.info.type > pj.info.type) {
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }
    int size() {
        int count = 0;
        Node pNode = head;
        while (pNode != null) {
            count++;
            pNode = pNode.next;
        }
        return count;
    }
    void f4() throws Exception {
        clear();
        loadData(14);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
       //code space
        sortByAge(0, 3);
       //code space

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

}
