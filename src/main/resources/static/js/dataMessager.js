let stompClient=null;
let searchScreen=document.getElementById('searchScreen');
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
                populate_list(responseObj);
                // history.innerHTML=responseObj.name;
                // view1.style.display='none';
                // view2.style.display='block';
                searchScreen.style.display = 'block';
            }else{
                console.log('Good Boy this is nottracked');

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

function populate_list(json_response)
{
    // Get the json response and the list-box element from the document
    const queries  = json_response['queries'];
    const list_box = document.querySelector("#list-box");
    // Clear the list_box
    list_box.innerHTML = '';
    // Add the new elements to list-box
    for (let i = 0;i < queries.length;i++)
    {
        const list_item = document.createElement('list-item');
        list_item.textContent = queries[i];
        list_box.append(list_item);
    }

}

let fingerprint=constructFingerPrint();
console.log(fingerprint);
let transferData={'fingerprint':fingerprint}
let k=createConnection(transferData);


