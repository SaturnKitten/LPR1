
package br.com.projeto_petshop.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_petshop.dto.FuncionarioDTO;
import br.com.projeto_petshop.ctr.FuncionarioCTR;

public class FuncionarioVIEW extends javax.swing.JInternalFrame {

    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    
    FuncionarioDTO funcionarioDTO = new FuncionarioDTO(); //Cria um objeto funcionarioDTO
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR(); //Cria um objeto tutorCTR
    
    int gravar_alterar;//Variável usada saber se está alterando ou incluindo
    
    ResultSet rs;//Variável usada para preenchimento da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_funcionario;//Variável para guardar o modelo da tabela
    
    /**
     * Creates new form FuncionarioVIEW
     */
    public FuncionarioVIEW() {
        initComponents();
        
        //Chama todos os métodos liberaCampos
        liberaCampos(false);
        //Chama o método liberaBotoes
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_funcionario = (DefaultTableModel) jtl_consultar_funcionario.getModel();
    }
    
    //Método para centralizar o internalFrame
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
    }//Fecha método setPosicao

    //Método utilizado para gravar os dados do funcionario
    private void gravar(){
        try{
            funcionarioDTO.setUser_funcionario(user_funcionario.getText());
            funcionarioDTO.setSenha_funcionario(String.valueOf(senha_funcionario.getPassword()));
            funcionarioDTO.setNome_funcionario(nome_funcionario.getText());
            funcionarioDTO.setCpf_funcionario(cpf_funcionario.getText());
            funcionarioDTO.setRg_funcionario(rg_funcionario.getText());
            funcionarioDTO.setDatanasc_funcionario(data_format.parse(datanasc_funcionario.getText()));
            funcionarioDTO.setGenero_funcionario(genero_funcionario.getSelectedItem().toString());
            funcionarioDTO.setTel_funcionario(tel_funcionario.getText());
            funcionarioDTO.setRua_funcionario(rua_funcionario.getText());
            funcionarioDTO.setNum_funcionario(num_funcionario.getText());
            funcionarioDTO.setCep_funcionario(cep_funcionario.getText());
            funcionarioDTO.setBairro_funcionario(bairro_funcionario.getText());
            funcionarioDTO.setCidade_funcionario(cidade_funcionario.getText());
            funcionarioDTO.setEstado_funcionario(estado_funcionario.getSelectedItem().toString());
            funcionarioDTO.setTipo_funcionario(tipo_funcionario.getSelectedItem().toString());
            
            JOptionPane.showMessageDialog(null, funcionarioCTR.inserirFuncionario(funcionarioDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao gravar" + e.getMessage());
        }
    }//Fecha método gravar
    
    //Método utilizado para alterar os dados do tutor
    private void alterar(){
        try{
            funcionarioDTO.setUser_funcionario(user_funcionario.getText());
            funcionarioDTO.setSenha_funcionario(String.valueOf(senha_funcionario.getPassword()));
            funcionarioDTO.setNome_funcionario(nome_funcionario.getText());
            funcionarioDTO.setCpf_funcionario(cpf_funcionario.getText());
            funcionarioDTO.setRg_funcionario(rg_funcionario.getText());
            funcionarioDTO.setDatanasc_funcionario(data_format.parse(datanasc_funcionario.getText()));
            funcionarioDTO.setGenero_funcionario(genero_funcionario.getSelectedItem().toString());
            funcionarioDTO.setTel_funcionario(tel_funcionario.getText());
            funcionarioDTO.setRua_funcionario(rua_funcionario.getText());
            funcionarioDTO.setNum_funcionario(num_funcionario.getText());
            funcionarioDTO.setCep_funcionario(cep_funcionario.getText());
            funcionarioDTO.setBairro_funcionario(bairro_funcionario.getText());
            funcionarioDTO.setCidade_funcionario(cidade_funcionario.getText());
            funcionarioDTO.setEstado_funcionario(estado_funcionario.getSelectedItem().toString());
            funcionarioDTO.setTipo_funcionario(tipo_funcionario.getSelectedItem().toString());
            
            if((check_alterarsenha.isSelected()) && (senha_funcionario.getPassword().length != 0)){
                funcionarioDTO.setSenha_funcionario(String.valueOf(senha_funcionario.getPassword()));
            }
            else{
                funcionarioDTO.setSenha_funcionario(null);
            }
            JOptionPane.showMessageDialog(null, funcionarioCTR.alterarFuncionario(funcionarioDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao alterar" + e.getMessage());
        }
    }//Fecha método alterar
    
    //Método utilizado para excluir os dados do tutor
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o funcionario?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, funcionarioCTR.excluirFuncionario(funcionarioDTO));
        };
    }//Fecha método excluir
    
    //Método utilizado para liberar/bloquear os campos da tela
    private void liberaCampos(boolean a){
        nome_funcionario.setEnabled(a);
        cpf_funcionario.setEnabled(a);
        rg_funcionario.setEnabled(a);
        datanasc_funcionario.setEnabled(a);
        genero_funcionario.setEnabled(a);
        tel_funcionario.setEnabled(a);
        rua_funcionario.setEnabled(a);
        num_funcionario.setEnabled(a);
        cep_funcionario.setEnabled(a);
        bairro_funcionario.setEnabled(a);
        cidade_funcionario.setEnabled(a);
        estado_funcionario.setEnabled(a);
        user_funcionario.setEnabled(a);
        senha_funcionario.setEnabled(a);
        check_alterarsenha.setEnabled(a);
        tipo_funcionario.setEnabled(a);
    }//Fecha método liberaCampos
    
    //Método utilizado para liberar os botões da tela
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btn_novo.setEnabled(a);
        btn_salvar.setEnabled(b);
        btn_cancelar.setEnabled(c);
        btn_excluir.setEnabled(d);
        btn_sair.setEnabled(e);
    }//Fecha método liberaBotoes
    
    //Método utilizado para limpar os campos da tela
    private void limpaCampos(){
        nome_funcionario.setText("");
        cpf_funcionario.setText("");
        rg_funcionario.setText("");
        datanasc_funcionario.setText("");
        tel_funcionario.setText("");
        rua_funcionario.setText("");
        num_funcionario.setText("");
        cep_funcionario.setText("");
        bairro_funcionario.setText("");
        cidade_funcionario.setText("");
        user_funcionario.setText("");
        senha_funcionario.setText("");
        //check_alterarsenha.setText("");
    }//Fecha método limpaCampos
    
    //Método utilizado para preencher/construir a jtable
    private void preencheTabela(String nome_funcionario){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_funcionario.setNumRows(0);
            //Enquanto tiver linhas - faça
            funcionarioDTO.setNome_funcionario(nome_funcionario);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 1); //i = é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_funcionario.addRow(new Object[]{
                   rs.getString("id_funcionario"),
                   rs.getString("nome_funcionario"),
                });
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            funcionarioCTR.CloseDB();
        }
    }//Fecha método preencheTabela
    
    private void preencheCampos(int id_funcionario) {
        try {
            funcionarioDTO.setId_funcionario(id_funcionario);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 2); // 2 = é a pesquisa no id na classe DAO
            if (rs.next()) {
                limpaCampos();

                nome_funcionario.setText(rs.getString("nome_funcionario"));
                cpf_funcionario.setText(rs.getString("cpf_funcionario"));
                rg_funcionario.setText(rs.getString("rg_funcionario"));

                //datanasc_tutor.setText(rs.getString("datanasc_tutor"));
                datanasc_funcionario.setText(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("datanasc_funcionario")));

                //genero_tutor.setSelectedItem("Feminino"); // Teste direto

                genero_funcionario.setSelectedItem(rs.getString("genero_funcionario"));
                tel_funcionario.setText(rs.getString("tel_funcionario"));
                rua_funcionario.setText(rs.getString("rua_funcionario"));
                num_funcionario.setText(rs.getString("num_funcionario"));
                cep_funcionario.setText(rs.getString("cep_funcionario"));
                bairro_funcionario.setText(rs.getString("bairro_funcionario"));
                cidade_funcionario.setText(rs.getString("cidade_funcionario"));
                estado_funcionario.setSelectedItem(rs.getString("estado_funcionario"));
                user_funcionario.setText(rs.getString("user_funcionario"));
                tipo_funcionario.setSelectedItem(rs.getString("tipo_funcionario"));

                gravar_alterar = 2;
                liberaCampos(true);
                senha_funcionario.setEnabled(false);
                check_alterarsenha.setSelected(false);
            }//Fecha if
        }//Fecha try
        catch (Exception erTab) {
            System.out.println("Erro SQL:" + erTab);
        }//Fecha catch
        finally {
            funcionarioCTR.CloseDB();        
        }//Fecha finally
    }//Fecha método preencheCampos

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_sair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_funcionario = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        btn_pesquisar = new javax.swing.JButton();
        pesquisa_nome = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nome_funcionario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        datanasc_funcionario = new javax.swing.JFormattedTextField();
        cpf_funcionario = new javax.swing.JFormattedTextField();
        rg_funcionario = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tel_funcionario = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        rua_funcionario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        num_funcionario = new javax.swing.JTextField();
        cep_funcionario = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        bairro_funcionario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cidade_funcionario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        estado_funcionario = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        genero_funcionario = new javax.swing.JComboBox<>();
        user_funcionario = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        senha_funcionario = new javax.swing.JPasswordField();
        check_alterarsenha = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        tipo_funcionario = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/novo.png"))); // NOI18N
        btn_novo.setText("Novo");
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/salvar.png"))); // NOI18N
        btn_salvar.setText("Salvar");
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/cancelar.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/excluir.png"))); // NOI18N
        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/sair.png"))); // NOI18N
        btn_sair.setText("Sair");
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtl_consultar_funcionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_funcionario.getTableHeader().setReorderingAllowed(false);
        jtl_consultar_funcionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_funcionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_funcionario);
        if (jtl_consultar_funcionario.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_funcionario.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_funcionario.getColumnModel().getColumn(0).setPreferredWidth(1);
            jtl_consultar_funcionario.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("CONSULTA");

        btn_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/pesquisar.png"))); // NOI18N
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel14.setText("Nome:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(143, 143, 143))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pesquisa_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_pesquisar)))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel15)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pesquisa_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(btn_pesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CADASTRO DE FUNCIONÁRIOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nome:");

        try {
            datanasc_funcionario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            cpf_funcionario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            rg_funcionario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("CPF:");

        jLabel4.setText("RG:");

        jLabel5.setText("Data de Nascimento:");

        try {
            tel_funcionario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Telefone:");

        jLabel7.setText("Rua:");

        try {
            cep_funcionario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setText("CEP:");

        jLabel11.setText("Cidade:");

        jLabel12.setText("Estado:");

        estado_funcionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        jLabel13.setText("Gênero:");

        genero_funcionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMININO", "NÃO-BINÁRIO", "HELICÓPTERO DE COMBATE" }));

        jLabel16.setText("User:");

        check_alterarsenha.setText("Alterar Senha");
        check_alterarsenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                check_alterarsenhaMouseClicked(evt);
            }
        });

        jLabel17.setText("Senha:");

        tipo_funcionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "COMUM", " " }));

        jLabel18.setText("Tipo:");

        jLabel19.setText("Número:");

        jLabel20.setText("Bairro:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cidade_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(estado_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tipo_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(num_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(bairro_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(genero_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(datanasc_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tel_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cep_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cpf_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(rg_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(nome_funcionario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rua_funcionario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(user_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel17)
                            .addGap(18, 18, 18)
                            .addComponent(senha_funcionario, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(check_alterarsenha))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(genero_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(datanasc_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpf_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rg_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tel_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cep_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rua_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bairro_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(num_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cidade_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(estado_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(senha_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(check_alterarsenha))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipo_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(348, 348, 348))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(btn_novo)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sair)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salvar)
                    .addComponent(btn_novo)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_excluir)
                    .addComponent(btn_sair))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }
        else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no sistema!");
            }
        }

        limpaCampos();
        modelo_jtl_consultar_funcionario.setNumRows(0);
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_funcionario.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_funcionario.setNumRows(0);

    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_sairActionPerformed

    private void jtl_consultar_funcionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_funcionarioMouseClicked
        //Pega o ID do tutor selecionado e chama preencheCampos
        preencheCampos(Integer.parseInt(String.valueOf(
            jtl_consultar_funcionario.getValueAt(
                jtl_consultar_funcionario.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_funcionarioMouseClicked

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        preencheTabela(pesquisa_nome.getText());
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void check_alterarsenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_check_alterarsenhaMouseClicked
        if(check_alterarsenha.isSelected()){
            senha_funcionario.setEnabled(true);
        }
        else{
            senha_funcionario.setEnabled(false);
            senha_funcionario.setText(null);
        }
    }//GEN-LAST:event_check_alterarsenhaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro_funcionario;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisar;
    private javax.swing.JButton btn_sair;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JFormattedTextField cep_funcionario;
    private javax.swing.JCheckBox check_alterarsenha;
    private javax.swing.JTextField cidade_funcionario;
    private javax.swing.JFormattedTextField cpf_funcionario;
    private javax.swing.JFormattedTextField datanasc_funcionario;
    private javax.swing.JComboBox<String> estado_funcionario;
    private javax.swing.JComboBox<String> genero_funcionario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_funcionario;
    private javax.swing.JTextField nome_funcionario;
    private javax.swing.JTextField num_funcionario;
    private javax.swing.JTextField pesquisa_nome;
    private javax.swing.JFormattedTextField rg_funcionario;
    private javax.swing.JTextField rua_funcionario;
    private javax.swing.JPasswordField senha_funcionario;
    private javax.swing.JFormattedTextField tel_funcionario;
    private javax.swing.JComboBox<String> tipo_funcionario;
    private javax.swing.JTextField user_funcionario;
    // End of variables declaration//GEN-END:variables
}
