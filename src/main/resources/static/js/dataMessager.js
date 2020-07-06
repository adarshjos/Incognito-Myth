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
    let socket = new SockJS('/incognitoMyth');
    stompClient = Stomp.over(socket);
    console.log('Connection Creation');
    stompClient.connect({}, (frame)=> {
        stompClient.subscribe("/app/fp/initFP/"+JSON.stringify(transferData), (responseData)=> {
            let responseObj=JSON.parse(responseData.body);
            console.log('status'+responseObj.status);
            if(responseObj.status==='FOUND'){
                console.log('AssHole this is tracked');
                console.log(responseObj['queries']);
                // history.innerHTML=responseObj.name;
                // view1.style.display='none';
                // view2.style.display='block';

            }else{
                console.log('Good Boy this is nottracked');
                // view1.style.display = 'block';
                // view2.style.display='none';
            }
        });
    });
}
function sendFPUserData(){
    let searchQuery=document.getElementById('searchQuery').value;
    let reqData={
        name:searchQuery,
        fingerprint:transferData.fingerprint
    };
    stompClient.send("/app/insertFP", {}, JSON.stringify(reqData));
    window.location.href = `https://www.google.co.in/search?q=${reqData.name}`;

}
function forgetFPUserData(){
    stompClient.send("/app/forgetFP",{},JSON.stringify(transferData));
    console.log('FP is deleted');
    location.reload();
}


let fingerprint=constructFingerPrint();
console.log(fingerprint);
let transferData={'fingerprint':fingerprint}
let k=createConnection(transferData);


