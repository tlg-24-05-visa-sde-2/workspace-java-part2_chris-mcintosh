package com.duckrace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


class DuckRacer {
    //Fields or instance variables
    private final int id;
    private final String name;
    private final Collection<Reward> rewards = new ArrayList<>();

    //Constructors
    public DuckRacer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Business or Action methods

    public void win(Reward reward){
        rewards.add(reward);
    }

    //Accessor methods
    public int getId () {
        return id;
    }

    public String getName () {
        return name;
    }


    //Derived property =  its derived from the size of the collection
    public int getWins () {
        return rewards.size();
    }

    //Instead of returning a direct reference to our internal 'rewards' collection
    // we return a "read-only view" of it
    public Collection<Reward> getRewards () {
        return Collections.unmodifiableCollection(rewards);
    }

    @Override
    public String toString(){
        return String.format("%s: id=%s, name=%s, wins=%s, rewards=%s",
                this.getClass().getSimpleName(), getId(), getName(), getWins(), getRewards());
            }
}