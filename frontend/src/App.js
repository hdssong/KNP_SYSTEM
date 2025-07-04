import {useEffect, useState} from "react";
import axios from "axios";

function App() {
  const [data, setData] = useState('');

    useEffect(() => {
        axios.get('/api/login')
            .then((res) => {
                setData(res.data)    
            })
            .catch(err => console.log(err))
    }, []);
    return (
        <div className="App">
            로그인 페이지세요: {data}
        </div> 
    );
}

export default App;
