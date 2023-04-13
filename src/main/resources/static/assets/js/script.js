function submitSuccess(inputNameTag, inputKeyTag){

    var randomKey = Math.floor(Math.random() * 100000000);

    if(allLetter(inputNameTag)){
    document.getElementById(inputKeyTag).value = randomKey;

    setTimeout(() => {if(document.getElementById('love-success-true').style.display == 'block' ||
                         document.getElementById('katy-success-true').style.display == 'block' ||
                         document.getElementById('regina-success-true').style.display == 'block'){
                            window.location.href = 'https://t.me/LoveNailBot?start=' + randomKey;
                         }
        } ,2000);
    }
}



function allLetter(uname){
    var name = document.getElementById(uname).value;
    var letters = /^[A-Za-z]+$/;
    var ruLetters = /^[А-Яа-я]+$/;
    if(name.match(ruLetters) || name.match(letters)){
        if(name.match(letters)){
        console.log('inLetOk');
        return true;
        }
        console.log('letOk');
        return true;
    }
    else{
        alert('Username must have alphabetcharactersonly');
        document.getElementById(uname).focus();
        return false;
    }
}
