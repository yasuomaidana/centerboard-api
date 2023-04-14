import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {
  const [message, setMessage] = useState("calling message");

    const message_request = async () =>{
        const response = await fetch("http://localhost:8080/hello");
        if (!response.ok) throw Error('Did not received expected data')
        const listItems = await response.text();
        console.log("loading from serv", listItems);
        setMessage(listItems);
    }
    message_request();
  return (<>
  <div>kotlin</div>
  {message}
  <img src='http://localhost:8080/hello/image'></img>
  </>);
}

export default App;
