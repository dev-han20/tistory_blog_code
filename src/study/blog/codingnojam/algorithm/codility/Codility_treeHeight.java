package study.blog.codingnojam.algorithm.codility;

class Codility_treeHeight {
    int result =0;
    public int solution(Tree T) {

        recursion(T, 0);
        return result;
    }

    public void recursion (Tree T, int count){

        if(T.l == null && T.r == null){
            result = Math.max(result, count);
            return ;
        }

        if(T.l != null){
            count++;
            recursion(T.l, count);
            count--;
        }

        if(T.r != null){
            count++;
            recursion(T.r, count);
        }

    }

    class Tree{
        int x;
        Tree l;
        Tree r;


    }
}