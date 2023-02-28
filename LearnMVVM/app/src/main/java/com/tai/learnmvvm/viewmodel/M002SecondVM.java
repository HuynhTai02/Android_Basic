package com.tai.learnmvvm.viewmodel;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class M002SecondVM extends ViewModel {
    private MutableLiveData<String> firstName = new MutableLiveData<>("");
    private MutableLiveData<String> lastName = new MutableLiveData<>("");
    private MutableLiveData<Boolean> isEN = new MutableLiveData<>(false);

    public void setInfo(String firstName, String lastName, boolean isEN) {
        this.firstName.postValue(firstName);
        this.lastName.postValue(lastName);
        this.isEN.postValue(isEN);
    }

    public MutableLiveData<Boolean> getIsEN() {
        return isEN;
    }

    public MutableLiveData<String> getFirstName() {
        return firstName;
    }

    public MutableLiveData<String> getLastName() {
        return lastName;
    }

    public MediatorLiveData<String> fullNameLD() {
        MediatorLiveData<String> mediator = new MediatorLiveData<>();

        Observer<? super Object> handleName = new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                //fullName
                if (isEN.getValue() != null && isEN.getValue()) {
                    mediator.postValue(firstName.getValue() + " " + lastName.getValue());
                } else {
                    mediator.postValue(lastName.getValue() + " " + firstName.getValue());
                }
            }
        };

        mediator.addSource(firstName, handleName);
        mediator.addSource(lastName, handleName);
        mediator.addSource(isEN, handleName);

        return mediator;
    }
}
