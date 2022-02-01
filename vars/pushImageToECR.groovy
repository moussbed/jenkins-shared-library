import com.mb.AWS

def call(String imageName){
    new AWS(this).pushDockerImageECR(imageName)
}