let stompClient=null;
let view1=document.getElementById('view1');
let view2=document.getElementById('view2');
let history=document.getElementById('history');
function constructFingerPrint(){
    console.log('Fingerprint Creating');
    let client=new ClientJS();
    return client.getFingerprint();
}
function createConnection(transferData){
    console.log('Adarsh Joseph');
    let socket = new SockJS('/icognitoMyth');
    stompClient = Stomp.over(socket);
    console.log('Connection Creation');
    stompClient.connect({}, (frame)=> {
        console.log('Bye all:');
        stompClient.subscribe("/app/fp/initFP/"+JSON.stringify(transferData), (responseData)=> {
            let responseObj=JSON.parse(responseData.body);
            console.log('status'+responseObj.status);
            if(responseObj.status==='FOUND'){
                console.log('AssHole this is tracked');
                history.innerHTML=responseObj.name;
                view1.style.display='none';
                view2.style.display='block';

            }else{
                console.log('Good Boy this is nottracked');
                view1.style.display = 'block';
                view2.style.display='none';
            }
        });
    });
}
function sendFPUserData(){
    let searchQuery=document.getElementById('searchQuery').value;
    let reqData={
        name:searchQuery,
        fingerprint:transferData.fingerPrint
    };
    stompClient.send("/app/insertFP", {}, JSON.stringify(reqData));
    window.location.href = `https://www.google.co.in/search?q=${reqData.name}`;

}
function forgetFPUserData(){
    stompClient.send("/app/forgetFP",{},JSON.stringify(transferData));
    console.log('FP is deleted');
    location.reload();
}

console.log('hello all dataTransfer.js calling...')

let fingerprint=constructFingerPrint();
console.log(fingerprint);
let transferData={'fingerPrint':fingerprint}
let k=createConnection(transferData);
console.log('get ready for fingerprint');


