package mz.co.macave.passwordgenerator.viewmodel

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    fun defineChars(
        includeCapitalLetters: Boolean,
        includeNonCapitalLetters: Boolean,
        includeNumbers: Boolean,
        includeSpecialCharacters: Boolean
    ) : List<String>{

        val letters = StringBuilder()

        if (includeCapitalLetters) {
            letters.append("A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z")
        }

        if (includeNonCapitalLetters) {
            letters.append("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z")
        }

        if (includeNumbers) {
            letters.append("1,2,3,4,5,6,7,8,9,0")
        }

        if (includeSpecialCharacters) {
            letters.append("!,$,%,&,#")
        }
        return letters.split(",")
    }
}