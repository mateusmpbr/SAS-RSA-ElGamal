import Crypto.Util.number
import random
import time
import string

bits = 1024 # número de bits que o número primo a ser gerado deve ter

def gera_msg(tam):
    msg = ''.join(random.choice(string.ascii_letters) for x in range(tam))
    return msg


def encrip(mensagem, chaveB, a, p):
    msg=[]
    for i in range(len(mensagem)):
        msg.append(ord(mensagem[i])*pow(chaveB,a,p))
    return msg


def decrip(msg, chaveA, b, p):
    result=[]
    for i in range(len(msg)):
        h = pow(chaveA,b,p)
        result.append(chr(int(msg[i]/h)))
    return result


def test(tam_msg):
    inicio_gerar_chaves = round(time.time() * 1000)

    p = Crypto.Util.number.getPrime(bits)
    g = random.randint(1, p-1)
    
    b = random.getrandbits(bits)
    chaveB = pow(g, b, p)

    a = random.getrandbits(bits)  # PRIVATE_KEY
    chaveA = pow(g, a, p)

    print("------------------------- Teste ElGamal -------------------------")
    print("Variáveis")
    print("p: ", p)
    print("g: ", g)
    print("b: ", b)
    print("chaveB: ", chaveB)
    print("a: ", a)
    print("chaveA: ", chaveA)
    print("----------")

    fim_gerar_chaves = round(time.time() * 1000)
    tempo_gerar_chaves = fim_gerar_chaves - inicio_gerar_chaves

    inicio_gerar_mensagem = round(time.time() * 1000)
    mensagem = gera_msg(tam_msg)
    fim_gerar_mensagem = round(time.time() * 1000)
    tempo_gerar_mensagem = fim_gerar_mensagem - inicio_gerar_mensagem


    # print("Encriptando a seguinte mensagem: ", mensagem)

    #########  Encriptação ###########
    inicio_criptografar = round(time.time() * 1000)
    mensagem_encriptada = encrip(mensagem,chaveB,a,p)
    fim_criptografar = round(time.time() * 1000)
    tempo_criptografar = fim_criptografar - inicio_criptografar
    

    # print("Mensagem encriptada: ", mensagem_encriptada)

    #########  Decriptação ###########
    inicio_descriptografar = round(time.time() * 1000)
    mensagem_descriptada = decrip(mensagem_encriptada,chaveA,b,p)
    mensagem_descriptada = ''.join(mensagem_descriptada)
    fim_descriptografar = round(time.time() * 1000)
    tempo_descriptografar = fim_descriptografar - inicio_descriptografar
    # print("Mensagem descriptada: ", mensagem_descriptada)

    sucess = mensagem == mensagem_descriptada
    
    print("Tamanho da mensagem: ", tam_msg , "caracteres")
    print("Mensagem descriptada com sucesso? ", sucess)
    print("----------")
    
    print("Tempo para gerar a mensagem: ", tempo_gerar_mensagem, "ms")
    print("Tempo para criar as chaves: ", tempo_gerar_chaves, "ms")
    print("Tempo para encriptar: ", tempo_criptografar, "ms")
    print("Tempo para descriptar: ", tempo_descriptografar, "ms")
    print("Tempo de execução geral: ",
    (tempo_gerar_mensagem + tempo_gerar_chaves + tempo_criptografar + tempo_descriptografar), "ms")

    if sucess: return 0
    return 1


def main():

    inicio_total = round(time.time() * 1000)
    failed = 0
    
    print("-------------------------------------------------- TESTES ElGamal --------------------------------------------------\n")
    
    failed += test(100)
    failed += test(200)
    failed += test(250)
    failed += test(300)
    failed += test(1000)

    fim_total = round(time.time() * 1000)
    tempo_total = fim_total - inicio_total

    print("\n--------------------------------------------------\n")
    print("Testes falhos: ", failed)
    print("Tempo de execução total: ", tempo_total,"ms")


if __name__ == '__main__':
    main()