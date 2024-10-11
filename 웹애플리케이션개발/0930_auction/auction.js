var web3 = new Web3('ws://localhost:7545');

var bidder; 
var accounts = [];  // 지갑 주소 저장
var selectedAccount; // 선택된 계좌

var auctionContract = new web3.eth.Contract(
    [
        {
            "inputs": [],
            "name": "bid",
            "outputs": [
                {
                    "internalType": "bool",
                    "name": "",
                    "type": "bool"
                }
            ],
            "stateMutability": "payable",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "cancel_auction",
            "outputs": [
                {
                    "internalType": "bool",
                    "name": "",
                    "type": "bool"
                }
            ],
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "inputs": [
                {
                    "internalType": "uint256",
                    "name": "_biddingTime",
                    "type": "uint256"
                },
                {
                    "internalType": "address",
                    "name": "_owner",
                    "type": "address"
                },
                {
                    "internalType": "string",
                    "name": "_brand",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "_Rnumber",
                    "type": "string"
                }
            ],
            "stateMutability": "nonpayable",
            "type": "constructor"
        },
        {
            "anonymous": false,
            "inputs": [
                {
                    "indexed": true,
                    "internalType": "address",
                    "name": "highestBidder",
                    "type": "address"
                },
                {
                    "indexed": false,
                    "internalType": "uint256",
                    "name": "highestBid",
                    "type": "uint256"
                }
            ],
            "name": "BidEvent",
            "type": "event"
        },
        {
            "inputs": [],
            "name": "cancelBid",
            "outputs": [
                {
                    "internalType": "bool",
                    "name": "",
                    "type": "bool"
                }
            ],
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "anonymous": false,
            "inputs": [
                {
                    "indexed": false,
                    "internalType": "uint256",
                    "name": "message",
                    "type": "uint256"
                },
                {
                    "indexed": false,
                    "internalType": "uint256",
                    "name": "time",
                    "type": "uint256"
                }
            ],
            "name": "CanceledEvent",
            "type": "event"
        },
        {
            "inputs": [],
            "name": "deactivateAuction",
            "outputs": [],
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "anonymous": false,
            "inputs": [
                {
                    "indexed": false,
                    "internalType": "enum Auction.auction_state",
                    "name": "newState",
                    "type": "uint8"
                }
            ],
            "name": "StateUpdated",
            "type": "event"
        },
        {
            "inputs": [],
            "name": "withdraw",
            "outputs": [
                {
                    "internalType": "bool",
                    "name": "",
                    "type": "bool"
                }
            ],
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "anonymous": false,
            "inputs": [
                {
                    "indexed": false,
                    "internalType": "address",
                    "name": "withdrawer",
                    "type": "address"
                },
                {
                    "indexed": false,
                    "internalType": "uint256",
                    "name": "amount",
                    "type": "uint256"
                }
            ],
            "name": "WithdrawalEvent",
            "type": "event"
        },
        {
            "inputs": [],
            "name": "withdrawRemainingFunds",
            "outputs": [],
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "auction_end",
            "outputs": [
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "auction_start",
            "outputs": [
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [
                {
                    "internalType": "address",
                    "name": "",
                    "type": "address"
                }
            ],
            "name": "bids",
            "outputs": [
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "get_owner",
            "outputs": [
                {
                    "internalType": "address",
                    "name": "",
                    "type": "address"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "highestBid",
            "outputs": [
                {
                    "internalType": "uint256",
                    "name": "",
                    "type": "uint256"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "highestBidder",
            "outputs": [
                {
                    "internalType": "address",
                    "name": "",
                    "type": "address"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "Mycar",
            "outputs": [
                {
                    "internalType": "string",
                    "name": "Brand",
                    "type": "string"
                },
                {
                    "internalType": "string",
                    "name": "Rnumber",
                    "type": "string"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        },
        {
            "inputs": [],
            "name": "STATE",
            "outputs": [
                {
                    "internalType": "enum Auction.auction_state",
                    "name": "",
                    "type": "uint8"
                }
            ],
            "stateMutability": "view",
            "type": "function"
        }
    ],
    '0x86B268Ea4F555CF3d28dEBaAe7A726375C0ed741'  // 여기에 실제 계약 주소를 넣어야 함
);

// 초기화 함수
function init() {
    web3.eth.getAccounts().then(function(acc) {
        accounts = acc.slice(0, 10);  // 첫 10개의 계정을 받음
        displayWallets();  // 지갑 주소 표시
        updateAuctionDetails();  // 경매 정보 업데이트
        displayWalletBids();  // 지갑별 입찰 금액 표시
        listenToEvents();  // 이벤트 수신 리스너 추가
    }).catch(function(error) {
        console.error("Error fetching accounts: ", error);
        alert("Error fetching accounts. Check the connection to the blockchain.");
    });
}

// 지갑 주소를 표시하는 함수
function displayWallets() {
    var walletDiv = document.getElementById('wallet_addresses');
    walletDiv.innerHTML = '';
    accounts.forEach(function(account, index) {
        walletDiv.innerHTML += `
            <div>
                <input type="radio" name="wallet" value="${account}" ${index === 0 ? 'checked' : ''} onClick="selectWallet('${account}')">
                ${account}
            </div>
        `;
    });
    selectedAccount = accounts[0];  // 기본적으로 첫 번째 지갑 선택
}

// 지갑을 선택하는 함수
function selectWallet(account) {
    selectedAccount = account;
    updateWalletBid(account);
}

// 입찰하는 함수 (선택된 지갑으로 입찰)
function bid() {
    var mybid = document.getElementById('value').value;
    if (isNaN(mybid) || mybid <= 0) {
        alert("Please enter a valid bid amount greater than 0.");
        return;
    }

    auctionContract.methods.bid().send({
        from: selectedAccount,  // 선택한 지갑 주소로 입찰
        value: web3.utils.toWei(mybid, "ether"),
        gas: 200000
    }).then(function(result) {
        console.log(result);
        document.getElementById("biding_status").innerHTML = "Successful bid, transaction ID : " + result.transactionHash;
        displayWalletBids();  // 입찰 후 지갑별 입찰 금액 업데이트
    }).catch(function(error) {
        console.error("Error during bid: ", error);
        alert("Failed to bid. Check the transaction details.");
    });
}

// 입찰 취소 함수
function cancelBid() {
    // 최고 입찰자의 입찰 취소를 허용하지 않음
    if (selectedAccount === document.getElementById('HighestBidder').innerHTML) {
        document.getElementById("cancel_bid_status").innerHTML = "You cannot cancel the highest bid.";
        return;
    }

    auctionContract.methods.cancelBid().send({
        from: selectedAccount, 
        gas: 200000
    }).then(function(result) {
        document.getElementById("cancel_bid_status").innerHTML = "Bid successfully canceled. Transaction ID: " + result.transactionHash;
        displayWalletBids();  // 입찰 취소 후 지갑별 입찰 금액 업데이트
    }).catch(function(error) {
        console.error("Error canceling bid: ", error);
        document.getElementById("cancel_bid_status").innerHTML = "Cancel bid failed: " + error.message;
    });
}

// 지갑별 입찰 금액을 표시하는 함수
function displayWalletBids() {
    var bidList = document.getElementById('bid_list');
    bidList.innerHTML = '';
    accounts.forEach(function(account) {
        auctionContract.methods.bids(account).call().then(function(result) {
            var bidEther = web3.utils.fromWei(web3.utils.toBN(result), 'ether');
            bidList.innerHTML += `<li>${account}: ${bidEther} ETH</li>`;
        }).catch(function(error) {
            console.error("Error fetching bids: ", error);
        });
    });
}

// 선택된 지갑의 입찰 금액을 업데이트하는 함수
function updateWalletBid(account) {
    auctionContract.methods.bids(account).call().then(function(result) {
        var bidEther = web3.utils.fromWei(web3.utils.toBN(result), 'ether');
        document.getElementById('MyBid').innerHTML = bidEther + ' ETH';
    }).catch(function(error) {
        console.error("Error updating wallet bid: ", error);
    });
}

// 경매 정보 업데이트 함수
function updateAuctionDetails() {
    auctionContract.methods.auction_end().call().then(function(result) {
        document.getElementById("auction_end").innerHTML = result;
    }).catch(function(error) {
        console.error("Error fetching auction end: ", error);
    });

    auctionContract.methods.highestBidder().call().then(function(result) {
        document.getElementById("HighestBidder").innerHTML = result;
    }).catch(function(error) {
        console.error("Error fetching highest bidder: ", error);
    });

    auctionContract.methods.highestBid().call().then(function(result) {
        var bidEther = web3.utils.fromWei(web3.utils.toBN(result), 'ether');
        document.getElementById("HighestBid").innerHTML = bidEther + ' ETH';
    }).catch(function(error) {
        console.error("Error fetching highest bid: ", error);
    });

    auctionContract.methods.STATE().call().then(function(result) {
        document.getElementById("STATE").innerHTML = result;
    }).catch(function(error) {
        console.error("Error fetching auction state: ", error);
    });

    auctionContract.methods.Mycar().call().then(function(result) {
        document.getElementById("car_brand").innerHTML = result.Brand;
        document.getElementById("registration_number").innerHTML = result.Rnumber;
    }).catch(function(error) {
        console.error("Error fetching car details: ", error);
    });
}

// 경매 취소 함수
function cancel_auction() {
    auctionContract.methods.cancel_auction().send({
        from: selectedAccount, 
        gas: 200000
    }).then(function(res) {
        console.log(res);
    }).catch(function(error) {
        console.error("Error canceling auction: ", error);
        alert("Failed to cancel auction.");
    });
}

// 출금 함수
function withdraw() {
    auctionContract.methods.withdraw().send({
        from: selectedAccount, 
        gas: 200000
    }).then(function(result) {
        document.getElementById("withdraw_status").innerHTML = "Withdraw successful, transaction ID: " + result.transactionHash;
    }).catch(function(error) {
        console.error("Error withdrawing funds: ", error);
        document.getElementById("withdraw_status").innerHTML = "Withdraw failed: " + error.message;
    });
}

// 이벤트를 수신하는 함수
function listenToEvents() {
    // 입찰 이벤트 (BidEvent) 수신
    auctionContract.events.BidEvent({}, function(error, event) {
        if (!error) {
            console.log("Bid Event: ", event);
            document.getElementById("eventslog").innerHTML += `${event.returnValues.highestBidder} has bid ${web3.utils.fromWei(event.returnValues.highestBid, 'ether')} ETH<br>`;
        } else {
            console.error("Error in BidEvent: ", error);
        }
    });

    // 경매 취소 이벤트 (CanceledEvent) 수신
    auctionContract.events.CanceledEvent({}, function(error, event) {
        if (!error) {
            console.log("Auction Canceled: ", event);
            document.getElementById("eventslog").innerHTML += `Auction canceled at ${event.returnValues.time}<br>`;
        } else {
            console.error("Error in CanceledEvent: ", error);
        }
    });

    // 출금 이벤트 (WithdrawalEvent) 수신
    auctionContract.events.WithdrawalEvent({}, function(error, event) {
        if (!error) {
            console.log("Withdrawal Event: ", event);
            document.getElementById("eventslog").innerHTML += `${event.returnValues.withdrawer} withdrew ${web3.utils.fromWei(event.returnValues.amount, 'ether')} ETH<br>`;
        } else {
            console.error("Error in WithdrawalEvent: ", error);
        }
    });
}
