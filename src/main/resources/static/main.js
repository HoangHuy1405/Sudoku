let tileInputs = document.querySelectorAll('.container .cellInput');

var rows = 9;
var cols = 9;
let inputArray = new Array(rows).fill(-1).map(() => new Array(cols).fill(-1));

// funciton to add inner border
function addInnerBody() {
    tileInputs.forEach((tileInput, index) => {
        // tileInput.value = index++; // for testing
        if(index % 9 == 2) {
            tileInput.style.borderRight = "2px solid black";
        }
        if(index % 9 == 6) {
            tileInput.style.borderLeft = "2px solid black";
        }
        if(Math.floor(index / 9) == 2) {
            tileInput.style.borderBottom = "2px solid black";
        }
        if(Math.floor(index / 9) == 6) {
            tileInput.style.borderTop = "2px solid black";
        }
    })
}

// generate data (GET method to get data from spring)
function generateBoard() {
    document.querySelector('.generateBtn').addEventListener('click', function(event) {
        event.preventDefault();
    
        // Call the API
        fetch('http://localhost:8080/api/generateArr')
            .then(response => {
                if(!response.ok) {
                    throw new Error(error);
                }
                return response.json();
            })
            .then(data => {
                console.log('Fetched data:', data);
                display(data);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    });
}

function display(data) {
    tileInputs.forEach(function(tileInput, index) {
        let row = Math.floor(index / 9);
        let col = index % 9;
        var num = data[row][col];
        
        if(num > 0) {
            tileInput.value = num;
            tileInput.readOnly = true;
            tileInput.style.backgroundColor = "rgb(82 229 209 / 0%)";
        } else {
            tileInput.value = null;
            tileInput.readOnly = false;
            tileInput.style.backgroundColor = "rgb(82 229 209 / 47%)";
        }
        //tileInput.setAttribute("readonly");
        inputArray[row][col] = data[row][col];
    })
}
function getInputData() {
    tileInputs.forEach(function(tileInput, index) {
        tileInput.addEventListener('input', function(j) {
            console.log(tileInput.value);
            if(!checkValid(tileInput.value)) {
                tileInput.value = null;
                inputArray[Math.floor(index / 9)][index % 9] = -1;
            } else {
                var row = Math.floor(index / 9)
                var col = index % 9;
                inputArray[row][col] = parseInt(tileInput.value);
    
                var rowColObject = {
                    x: row,
                    y: col,
                };
                //sendData(rowColObject);
            }
            console.log("changing!");
            // console.log(tileInput.value)
            console.log(inputArray);
    
        })
    })
}
function checkValid(input) {
    if(input > 0 && input < 10) {
        return true;
    }
    return false;
}
function sendData() {
    document.querySelector('.submitBtn').addEventListener('click', function(event) {
        checkValidInput();
    })
}

function checkValidInput() {
    var isValid = true;
    // tileInputs.forEach((tileInput, index) => {
    //     if(tileInput.value == -1) {
    //         isValid = false;
    //         return;
    //     }
    // })
    inputArray.forEach((row, index) => {
        row.forEach((cellValue) => {
            if(cellValue == -1) {
                isValid = false;
            }
        })
    })
    console.log(inputArray)
    if(!isValid) {
        console.log("invalid input")
        alert("invalid input");
        return ;
    }
    // confirm before do this
    if(!confirm("Are you sure you want to submit?")) {
        return;
    }
    // fetch data
    fetch('http://localhost:8080/api/generateArr/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(inputArray)
    }) 
    .then(response => response.json())
    .then(data => {
        console.log('Success: ', data)
        printResult(data)
    })
    .catch((error) => {
        console.error('Error:', error)
    })
    
}
function printResult(result) {
    if(result) {
        alert("Winner winner Chicken Dinner!")
    } else {
        alert("Losttt!")
    }
}

// Order of execute
addInnerBody();
generateBoard();
getInputData();
sendData();




