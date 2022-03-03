package com.example.rng.manager;

import java.util.ArrayList;


class MemorySequenceMgr {
    protected ArrayList<Integer> sequencearray = new ArrayList<Integer>();
    protected static int maxSequenceSize = 0;

    //constructors
    public MemorySequenceMgr(){}
    public MemorySequenceMgr(int maxSequenceSize) {
        this.maxSequenceSize = maxSequenceSize;
    }

    protected void setMaxSequenceSize(int maxSequenceSize){
        this.maxSequenceSize = maxSequenceSize;
    }

    protected int getMaxSequenceSize(){return this.maxSequenceSize; }

    protected int getSequenceSize(){
        return sequencearray.size();
    }

    protected void clearSequence(){
        this.sequencearray.clear();
        this.maxSequenceSize = 0;
    }

    protected void generateRandomSequence(int gridsize){
        ArrayList<Integer> gridlist = new ArrayList<Integer>();
        int i;
        for (i=0; i< this.maxSequenceSize; i++){
            int value = (int) ((Math.random() * (gridsize - 1)) + 1);
            this.sequencearray.add(value);
        }
        return;
    }

    protected int getSequenceElement(int i){
        return sequencearray.get(i);
    }

    protected void addToSequence(int num){
        this.sequencearray.add(num);
    }

    protected int compareTo(MemorySequenceMgr memorySequenceMgr) {
        int i;
        for(i=0; i < memorySequenceMgr.getSequenceSize(); i++){
            if (memorySequenceMgr.getSequenceElement(i) != this.sequencearray.get(i)){
                return -1;
            }
        }
        return memorySequenceMgr.getSequenceSize();
    }
}

