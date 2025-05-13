from fastapi import FastAPI
import uvicorn
app = FastAPI()


@app.get("/1")
def exemplo():
    return {"Doramas Assistidos": ["A Business Proposal"]}

@app.post("/")
def dorama_post():
    return {"Inserir novo dorama": ["The Glory"]}

if __name__ == "__main__":
    uvicorn.run(app, port=8000)