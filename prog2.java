//Michael Merabi  -  ID# 109481201
//CS282 - Tuesday/Thursday Class
//Assignment #2   -  Assignment turned in on: 10/09/18

/* This is a project that contains an AVL tree and can traverse the tree in many different ways
 * including finding height, balancing and insert and finding leaf counts*/

//Getters and setters, constructors
class StringAVLNode {
    private String item;
    private int balance;
    private StringAVLNode left, right;

    public StringAVLNode(String str) {
        this.item=str;
        this.left=null;
        this.right=null;
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int bal) {
        this.balance=bal;
    }

    public String getItem() {
        return item;
    }

    public StringAVLNode getLeft() {
        return left;
    }

    public void setLeft(StringAVLNode pt) {
        this.left=pt;
    }

    public StringAVLNode getRight() {
        return left;
    }

    public void setRight(StringAVLNode pt) {
        this.right=pt;
    }

    public int height() { // Calculate height through the root recursively
        //per professor Wiegly's recommendation

        int lh = 0; // left height
        int rh = 0; //right height
        if (right!=null)
            rh = right.height();
        rh = rh + 1;
        int max = rh;

        if (left!=null)
            lh = left.height();
        lh = lh+1;
        if (lh > max)
            max = lh;
        return max;
    }
}   //End of constructor class

class StringAVLTree {
    StringAVLNode root;

    public StringAVLTree() {
        this.root = null;
    }
    // Rotate the node to the right ========= DONE ============
    private static StringAVLNode rotateRight(StringAVLNode t) {
        StringAVLNode Replacet;
        StringAVLNode PHNode; //Placeholder Node

        Replacet = t.getLeft();
        PHNode = Replacet.getRight();
        Replacet.setRight(t);
        t.setLeft(PHNode);  // Complete rotation

        return Replacet;
    }

    // Rotate the node to the left ======DONE=========
    private static StringAVLNode rotateLeft(StringAVLNode t) {
        StringAVLNode Replacet;
        StringAVLNode PHNode;  //Placeholder Node

        Replacet = t.getRight();
        PHNode = Replacet.getLeft();
        Replacet.setLeft(t);
        t.setRight(PHNode); //Completes the rotation

        return Replacet;
    }

    //--------DONE ------------
    public int height() {
        if (root == null)
            return 0;
        else
            return root.height();
    }

    // ------------DONE ----------------
    public int leafCt() { // public method calls private recursive leafRecursive
        StringAVLNode leafCt = root;
        return leafRecursive(leafCt);
    }

    private int leafRecursive(StringAVLNode leaf) {
        //Called by leafCt to recursively count amount of leaves in tree
        int recursiveCt;
        if (leaf == null) {
            recursiveCt = 0;
        }//if bottom of branch
        else if (leaf.getLeft() == null && leaf.getRight() == null) {
            recursiveCt = 1;
        } else {//start on left and go down then add to the right side
            recursiveCt = leafRecursive(leaf.getLeft()) + leafRecursive(leaf.getRight());
        }
        return recursiveCt;
    }

    // // ------------DONE ----------------
    public int balanced() { // return number of balanced AVL nodes by calling recursive balance
        int balanceHolder;
        balanceHolder = balancedRecursive(root);
        return balanceHolder;
    }

    private int balancedRecursive(StringAVLNode t) {
        int balCount;
        if (t == null) {
            balCount = 0;
        } else if (t.getBalance() == 0) {
            balCount = balancedRecursive(t.getLeft()) + balancedRecursive(t.getRight()) + 1;
        } else { //checking by going through three cases and adding recursively of left then right
            balCount = balancedRecursive(t.getLeft()) + balancedRecursive(t.getRight());
        }
        return balCount;
    }
    // Return the in order successor or null if there is none or str is not in the tree
    public String successor(String str) {
        StringAVLNode firstNode=null; //save bottom node

        //check to make sure it isn't null
        if(root==null){
            return null;
        }
        return successor(str, root, firstNode);
    }

    //Real Successor Method
    //third node to easily save node when string is found at bottom
    private String successor(String string, StringAVLNode parent, StringAVLNode firstNode) {
        StringAVLNode PHNode; //holding node
        String tstring = ""; //testing string

        if (parent == null) {//Case for null node
            tstring = null;
        }
        //Checking by comparing String to find match
        else if (string.compareTo(parent.getItem()) == 0) {
            if (parent.getRight() != null) {
                PHNode = parent.getRight();
                while (PHNode.getLeft() != null) {
                	//continue going down tree till null to test
                    PHNode = PHNode.getLeft();
                }
              tstring = PHNode.getItem();
            } else if (firstNode == null) {
                tstring = null;
            } else {
                tstring=firstNode.getItem();
            	}
        }
        //Set test string equal to successor
        else if(string.compareTo(parent.getItem())<0){
            tstring=successor(string ,parent.getLeft(),parent);
        } else {
            tstring=successor(string,parent.getRight(),firstNode);
        	}
        return tstring;
    }

    public void insert(String str) {
        root = insert(str, root);
    }

    private StringAVLNode insert(String str, StringAVLNode t) {
        int startbal; //before adjusting
        int endbal; //after adjusting (comparison
        int insbal; //another iteration of saving balance

        if (t == null) { // if root is null, this is the first node
            t = new StringAVLNode(str);
            t.setBalance(0);
        }

        //t is already in the tree
        else if (t.getItem().compareTo(str) > 0) { //left insert
            if (t.getLeft() != null) { //in case it is null
                startbal = t.getLeft().getBalance();//save bal before
                t.setLeft(insert(str, t.getLeft())); //adding to the left side
                endbal = t.getLeft().getBalance();//save bal after

                if (startbal == 0 && endbal != 0) {
                    t.setBalance(t.getBalance() - 1);
                }
            } else {
                t.setLeft(insert(str, t.getLeft()));
                t.setBalance(t.getBalance() - 1);
            	}
        } else {
            if (t.getRight() != null) { //right insert
                startbal = t.getRight().getBalance();
                t.setRight(insert(str, t.getRight())); // adding to the right side
                endbal = t.getRight().getBalance();

                if (startbal == 0 && endbal != 0) {
                    t.setBalance(t.getBalance() + 1);
                }
            } else {
                t.setRight(insert(str, t.getRight()));
                t.setBalance(t.getBalance() + 1);
            }
        }
        // Once node has been inserted and balance checked
        // Checks to see if rotations are needed
        if (t.getBalance() == 2 || t.getBalance() == -2) {
            if (t.getBalance() == 2) { // right heavy case
                if (t.getRight().getBalance() < 0) { //requires double rotation
                    insbal = t.getRight().getLeft().getBalance();
                    t.setRight(rotateRight(t.getRight()));
                    t = rotateLeft(t);
                    if (insbal == 0) { //equalized parent+Child
                        t.setBalance(0);
                        t.getRight().setBalance(0);
                        t.getLeft().setBalance(0);
                    } else if (insbal == 1) {
                        t.setBalance(0);
                        t.getRight().setBalance(0);
                        t.getLeft().setBalance(-1);
                    } else if (insbal == -1) {
                        t.setBalance(0);
                        t.getLeft().setBalance(0);
                        t.getRight().setBalance(1);
                    }
                } else { //normal left rotate
                    t = rotateLeft(t);
                    t.setBalance(0);
                    t.getLeft().setBalance(0);
                }
            } else {
                if (t.getLeft().getBalance() > 0) {
                    insbal = t.getLeft().getRight().getBalance();
                    t.setLeft(rotateLeft(t.getLeft()));
                    t = rotateRight(t);

                    if (insbal == 0) {
                        t.setBalance(0);
                        t.getRight().setBalance(0);
                        t.getLeft().setBalance(0);
                    } else if (insbal == 1) {
                        t.setBalance(0);
                        t.getRight().setBalance(0);
                        t.getLeft().setBalance(-1);
                    } else if (insbal == -1) {
                        t.setBalance(0);
                        t.getLeft().setBalance(0);
                        t.getRight().setBalance(1);
                    }
                } else {
                    t = rotateRight(t);
                    t.setBalance(0);
                    t.getRight().setBalance(0);
                }
            }
        }
        // end of rotations
        return t;
    } // end of insert

    public static String myName() {
        return "Michael Merabi";
    }

    //Main method starts here ===========
    public static void main(String args[]){
        StringAVLTreeXtra t = new StringAVLTreeXtra();
        String str;
        int ct, ran = 87, ransave=0, line = 1, ansct=0, num;
        boolean delete = false;
        char action;
        String s = "oimaoinaoioaoipaoiqaoilaoikaoikdikgikfikeoinaikgikaiqaioaoI20oI99onI30os30";
        String ans[] = {
                "0 0 0",
                "(ma)(0)1 1 1",
                "(ma(na))(1(0))1 2 1",
                "((ma)na(oa))((0)0(0))2 2 3",
                "((ma)na(oa(pa)))((0)1(1(0)))2 3 2",
                "((ma)na((oa)pa(qa)))((0)1((0)0(0)))3 3 4",
                "(((la)ma)na((oa)pa(qa)))(((0)-1)0((0)0(0)))3 3 5",
                "(((ka)la(ma))na((oa)pa(qa)))(((0)0(0))0((0)0(0)))4 3 7",
                "(((ka)kd((ke)kf))kg((la(ma))na((oa)pa(qa))))(((0)1((0)-1))0((1(0))0((0)0(0))))5 4 8",
                "(((ka)kd((ke)kf))kg((la(ma))na((oa)pa(qa))))(((0)1((0)-1))0((1(0))0((0)0(0))))5 4 8",
                "((((((aqu)cdf)ejc((hdo)ka(kae)))kd((ke)kf))kg(((la)lhx(lzc))ma(mlh(mrg))))na((((naj)oa(orq))pa(pln(pqr)))qa(((qiq(sgb))tvb(uem))uwp(yfo(zif)))))((((((0)-1)0((0)0(0)))-1((0)-1))-1(((0)0(0))0(1(0))))0((((0)0(0))0(1(0)))1(((1(0))-1(0))-1(1(0)))))13 6 20",
                "(((((((aka(apa))aqs(aqu(axb)))cdf(((cff)chj)clw((cug)cxs(ddg))))dkr((dpx)dsx((dvd)dyp(edc))))ejc((((epa)esn((fgq)fwu))gbg((giz(gly))gma(gws)))hdo((((hkx)hqv)iry((ive)ixt(ixy)))jgz((joh)jyc))))ka((((kae(kao))kd((ke)kf))kg((((kjj)kpc)la(lds))lev(lhx(lne))))loj(((lsc)lzc)ma(((mfc)mlh)mrg(mrj(mwq))))))na((((((naj)net(nmn))nnq((nps)nvq))oa(((ocx)ogx((onb)orq(otq)))pa((pln)pph(pqr(pzv)))))qa((((qei)qez(qiq))qpx((qrs)qsm((qyc)rfn)))rku(((rmq)rpr((rxu)seh))sgb(((shl)shv(ssz))tdl(tje(tqf))))))tvb((((ucx)uel((uem)uio(uje)))uwp((((vco)vik(vlx))vod((vzc)wdk(wkp)))wmh((wua)xic)))xjt((((xmp)xvh(xvz))yan(ybh))yfo(((yil)ynt(ysk))zfc(zif(zqc)))))))(((((((1(0))0(1(0)))0(((0)-1)0((0)0(0))))-1((0)1((0)0(0))))0((((0)1((0)-1))0((1(0))-1(0)))0((((0)-1)0((0)0(0)))-1((0)-1))))0((((1(0))0((0)-1))1((((0)-1)-1(0))-1(1(0))))-1(((0)-1)1(((0)-1)0(1(0))))))0((((((0)0(0))0((0)-1))1(((0)1((0)0(0)))0((0)1(1(0)))))0((((0)0(0))1((0)1((0)-1)))0(((0)1((0)-1))0(((0)0(0))0(1(0))))))0((((0)1((0)0(0)))1((((0)0(0))0((0)0(0)))-1((0)-1)))-1((((0)0(0))-1(0))0(((0)0(0))0(1(0)))))))55 8 88",
                "(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc(((hcr)icj)iei((iod)khx(lov))))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))0(((0)-1)0((0)0(0))))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))14 6 23",
                "hcr nfn yif zue bpj fcl lov nrm mld iod gxc sjw cfw bxa yzm xeh pih NULL sjv vkg our njv iei znv cer khx wcs icj pmt cst NULL NULL NULL NULL NULL ",
        };

        do {
            action = s.charAt(0);
            if (action == 'i') {   // insert
                str = s.substring(1,3);
                s = s.substring(3, s.length());
                t.insert(str);
            } else if (action == 'j') {   // insert
                str = s.substring(1,4);
                s = s.substring(4, s.length());
                t.insert(str);
            } else if (action == 'n') {  // new tree -- wipe out the tree and start over
                s = s.substring(1, s.length());
                t = new StringAVLTreeXtra();
            } else if (action == 'I') {
                ransave = ran;
                num = (s.charAt(1) - '0') * 10 + s.charAt(2) - '0';
                s = s.substring(3, s.length());
                for (ct = 1; ct <= num; ct++) {
                    ran = (ran * 101 + 103) % 1000003;
                    str= String.valueOf((char) (ran%26+'a'));
                    ran = (ran * 101 + 103) % 1000003;
                    str+= String.valueOf((char) (ran%26+'a'));
                    ran = (ran * 101 + 103) % 1000003;
                    str+= String.valueOf((char) (ran%26+'a'));
                    t.insert(str);
                }
            } else if (action == 's') {
                String res = new String(), succ;
                ran = ransave;
                num = (s.charAt(1) - '0') * 10 + s.charAt(2) - '0';
                s = s.substring(3, s.length());
                for (ct = 1; ct <= num; ct++) {
                    ran = (ran * 101 + 103) % 1000003;
                    str= String.valueOf((char) (ran%26+'a'));
                    ran = (ran * 101 + 103) % 1000003;
                    str+= String.valueOf((char) (ran%26+'a'));
                    ran = (ran * 101 + 103) % 1000003;
                    str+= String.valueOf((char) (ran%26+'a'));
                    succ = t.successor(str);
                    if (succ != null)
                        res += succ + " ";
                    else
                        res += "NULL ";
                }
                succ = t.successor("aaa");
                if (succ != null)
                    res += succ + " ";
                else
                    res += "NULL ";
                succ = t.successor("ccc");
                if (succ != null)
                    res += succ + " ";
                else
                    res += "NULL ";
                succ = t.successor("nnn");
                if (succ != null)
                    res += succ + " ";
                else
                    res += "NULL ";
                succ = t.successor("vvv");
                if (succ != null)
                    res += succ + " ";
                else
                    res += "NULL ";
                succ = t.successor("zzz");
                if (succ != null)
                    res += succ + " ";
                else
                    res += "NULL ";
                if (res.compareTo(ans[ansct]) == 0)
                    System.out.println("     Answers match in successor.   ");
                else {
                    System.out.println("   *** NO MATCH IN SUCCESSOR ***   ");
                    System.out.println(res);
                }
                t.display(); System.out.println();
                ansct++;
            } else {  // no other choice, then compare
                s = s.substring(1, s.length());
                System.out.print(line++ + ". ");
                if (t.toString2().compareTo(ans[ansct]) == 0) {
                    System.out.print(" Answers match.   ");
                    if ( line % 4 == 1)
                        System.out.println();
                }
                else {
                    System.out.println("   *** NO MATCH ***   ");
                    System.out.println(t.toString2());
                }
                t.display(); System.out.println();
                ansct++;
            }
        } while (s.length() != 0);
        System.out.println("Programmed by: " + StringAVLTree.myName());
    }
}

class StringAVLTreeXtra extends StringAVLTree {

    public StringAVLTreeXtra() {
        super();
    }

    public StringAVLNode getRoot() {
        return root;
    }

    public void display() {
        paren_out(getRoot());
        System.out.println();
        bal_out(getRoot());
    }

    public void paren_out(StringAVLNode t) {
        if (t == null) {
        } else {
            System.out.print("(");
            paren_out(t.getLeft());
            if (t.getItem().length() <= 1)
                System.out.print(" ");
            System.out.print(t.getItem());
            paren_out(t.getRight());
            System.out.print(")");
        }
    }

    public void bal_out(StringAVLNode t) {
        if (t == null) {
        } else {
            System.out.print("(");
            bal_out(t.getLeft());
            if (t.getItem().length() == 3)
                System.out.print(" ");
            if (t.getBalance() == -1)
                System.out.print(t.getBalance());
            else
                System.out.print(" " + t.getBalance());
            bal_out(t.getRight());
            System.out.print(")");
        }
    }

    public String paren_out1(StringAVLNode t) {
        String s;
        if (t == null) {
            s = new String();
        } else {
            s="(" + paren_out1(t.getLeft())+t.getItem()+paren_out1(t.getRight())+")";
        }
        return s;
    }

    public String bal_out1(StringAVLNode t) {
        String s;
        if (t == null) {
            s = new String();
        } else {
            s="("+bal_out1(t.getLeft())+t.getBalance()+bal_out1(t.getRight())+")";
        }
        return s;
    }

    public String toString2() {
        String s = new String();
        s = paren_out1(getRoot())+bal_out1(getRoot())+String.valueOf(this.leafCt())+" "+
                String.valueOf(this.height())+" "+String.valueOf(this.balanced());
        return s;
    }
}